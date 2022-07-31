package org.andot.share.app.line.core.handler;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.andot.share.app.line.config.ComLineConfig;
import org.andot.share.app.line.core.domain.ComLineMessage;
import org.andot.share.app.line.core.domain.ComLineMessageBody;
import org.andot.share.app.line.core.domain.ComLineMessageHeader;
import org.andot.share.app.line.util.HttpRequestParamUtil;


/**
 * 服务端处理器
 * @author andot
 */
@Slf4j
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.err.println();
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
            String lineId = HttpRequestParamUtil.getLineId(fullHttpRequest.uri());
            if (!ComLineConfig.getUserChannelMap().containsKey(lineId)) {
                ComLineConfig.getUserChannelMap().put(lineId, ctx);
            } else {
                log.info("已经登录过了！");
            }
        } else {
            TextWebSocketFrame request = (TextWebSocketFrame) msg;
            log.info("开始接收"+ request.text());
            ComLineMessage comLineMessage = JSONObject.parseObject(request.text(), ComLineMessage.class);
            System.err.println(comLineMessage.getBody().getContent());


            String toLineId = comLineMessage.getHeader().getToLineId();
            if (ComLineConfig.getUserChannelMap().containsKey(toLineId)) {
                ComLineMessage toComLineMessage = new ComLineMessage();

                ComLineMessageHeader header = new ComLineMessageHeader();
                header.setLineId(toLineId);
                header.setMsgType(1);
                header.setToLineId(comLineMessage.getHeader().getLineId());
                toComLineMessage.setHeader(header);

                ComLineMessageBody body = new ComLineMessageBody();
                body.setContent(comLineMessage.getBody().getContent());
                comLineMessage.setBody(body);

                comLineMessage.setFooter(comLineMessage.getFooter());

                ComLineConfig.getUserChannelMap().get(toLineId).channel().writeAndFlush(
                        new TextWebSocketFrame(JSONObject.toJSONString(toComLineMessage)));
            } else {
                log.error("离线了！");
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("接收完成");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("接收异常");
        cause.printStackTrace();
        log.error("服务端接收消息异常出现：", cause.getMessage());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        ComLineMessage comLineMessage = new ComLineMessage();
        ComLineMessageBody body = new ComLineMessageBody();
        body.setContent("登录成功！");
        comLineMessage.setBody(body);
        ctx.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(comLineMessage)));
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ComLineMessage comLineMessage = new ComLineMessage();
        ComLineMessageBody body = new ComLineMessageBody();
        body.setContent("退出登录成功！");
        comLineMessage.setBody(body);
        ctx.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(comLineMessage)));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ComLineMessage comLineMessage = new ComLineMessage();
        ComLineMessageBody body = new ComLineMessageBody();
        body.setContent("回复中！");
        comLineMessage.setBody(body);
        ctx.writeAndFlush(new TextWebSocketFrame(JSONObject.toJSONString(comLineMessage)));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }
}
