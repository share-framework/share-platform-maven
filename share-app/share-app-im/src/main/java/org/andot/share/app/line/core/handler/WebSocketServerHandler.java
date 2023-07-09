package org.andot.share.app.line.core.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import lombok.extern.slf4j.Slf4j;
import org.andot.share.app.line.config.ComLineConfig;
import org.andot.share.app.line.core.domain.ComLineMessage;
import org.andot.share.app.line.util.HttpRequestParamUtil;
import org.andot.share.app.line.util.MessageSaveUtil;
import org.andot.share.common.components.ShareValueComponent;
import org.andot.share.common.exception.TokenExpiredRuntimeException;
import org.andot.share.common.utils.JwtUtil;

import java.nio.charset.StandardCharsets;

/**
 * websocket 服务器端处理类
 * @author andot
 */
@Slf4j
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketFrame(ctx, (WebSocketFrame) msg);
        } else {
            log.warn("暂时没有适配消息");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private void handleHttpRequest (ChannelHandlerContext ctx,
                                    FullHttpRequest fullHttpRequest) {
        // 如果HTTP解码失败，返回HTTP异常
        if (!fullHttpRequest.decoderResult().isSuccess() ||
                !"websocket".equalsIgnoreCase(fullHttpRequest.headers().get("Upgrade"))) {
            sendHttpResponse(ctx, fullHttpRequest, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
        }
        // 获取请求头

//        HttpHeaders headers = fullHttpRequest.headers();
//        String token = headers.get(HttpHeaderNames.AUTHORIZATION);
        String token = HttpRequestParamUtil.getToken(fullHttpRequest.uri());
        // 判断token有效期
        if (JwtUtil.isTokenExpired(token)) {
            // Token 失效，返回 401 响应码
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.UNAUTHORIZED);
            ctx.writeAndFlush(response);
            return;
        }
        String lineId = HttpRequestParamUtil.getLineIdByAuthorization(token);
        // 加入用户存储
        String channelId = ctx.channel().id().asLongText();
        if (!ComLineConfig.getUserIdMap().containsKey(channelId)) {
            ComLineConfig.getUserIdMap().put(channelId, lineId);
            ComLineConfig.getUserChannelMap().put(HttpRequestParamUtil.getLineId(fullHttpRequest.uri()), ctx);
            log.info(ComLineConfig.getUserIdMap().get(channelId) + "已经上线");
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "/ws/conn", null, false);
        handshaker = wsFactory.newHandshaker(fullHttpRequest);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), fullHttpRequest);
        }
    }

    private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof  PingWebSocketFrame) {
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 仅支持文本消息，不支持二进制消息
        if (frame instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binaryWebSocketFrame = (BinaryWebSocketFrame) frame;
            ByteBuf buf = binaryWebSocketFrame.content();
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            String message = new String(bytes, StandardCharsets.UTF_8);
            ComLineMessage data = JSONObject.parseObject(message.substring(11), ComLineMessage.class);
            /**
             * 存储消息记录
             */
            MessageSaveUtil.addMessage(data);
            String lineId = data.getHeader().getToLineId();
            if (ComLineConfig.getUserChannelMap().containsKey(lineId)) {
                log.info("{}消息收到：{}, 准备转发！", lineId, message);
                ComLineConfig.getUserChannelMap().get(lineId).channel().writeAndFlush(binaryWebSocketFrame);
                log.info("{}消息转发成功！", lineId, message);
            }
            log.info(lineId + "消息收到：" + message);
        } else if (frame instanceof TextWebSocketFrame) {
            // 返回应答消息
            String request = ((TextWebSocketFrame)frame).text();
            ComLineMessage data = JSONObject.parseObject(request, ComLineMessage.class);
            /**
             * 存储消息记录
             */
            MessageSaveUtil.addMessage(data);

            String lineId = data.getHeader().getToLineId();

            if (ComLineConfig.getUserChannelMap().containsKey(lineId)) {
                log.info("{}消息收到：{}, 准备转发！", lineId, request);
                ComLineConfig.getUserChannelMap().get(lineId).channel().writeAndFlush(new TextWebSocketFrame(request));
                log.info("{}消息转发成功！", lineId, request);
            }

            log.info(lineId + "消息收到：" + request);
            ctx.channel().write(new TextWebSocketFrame(request));
        } else {
            throw new UnsupportedOperationException(String.format("%s frame types not suppoorted", frame.getClass().getName()));
        }
    }

    private static void sendHttpResponse (ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
        // 返回应答给客户端消息
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), StandardCharsets.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
            HttpUtil.setContentLength(res, res.content().readableBytes());
        }

        ChannelFuture future = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            future.addListener(ChannelFutureListener.CLOSE);
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        String channelId = ctx.channel().id().asLongText();
        if (ComLineConfig.getChannelMap().containsKey(channelId)) {
            ComLineConfig.getChannelMap().put(channelId, ctx);
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String channelId = ctx.channel().id().asLongText();
        if (ComLineConfig.getUserIdMap().containsKey(channelId)) {
            log.info(ComLineConfig.getUserIdMap().get(channelId) + "已经离线");
            ComLineConfig.getUserIdMap().remove(channelId);
        }
        if (ComLineConfig.getChannelMap().containsKey(channelId)) {
            ComLineConfig.getChannelMap().remove(channelId);
        }
    }
}
