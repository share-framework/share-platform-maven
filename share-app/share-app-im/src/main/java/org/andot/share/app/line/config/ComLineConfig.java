package org.andot.share.app.line.config;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * netty 服务配置
 * @author andot
 */
public class ComLineConfig {
    private ComLineConfig() {}

    /**
     * 用户同道存储
     */
    private static Map<String, String> userIdMap = new ConcurrentHashMap<>(128);
    private static Map<String, ChannelHandlerContext> getUserChannelMap = new ConcurrentHashMap<>(128);
    private static Map<String, ChannelHandlerContext> channelMap = new ConcurrentHashMap<>(128);

    public static Map<String, String> getUserIdMap() {
        return userIdMap;
    }

    public static Map<String, ChannelHandlerContext> getUserChannelMap() {
        return getUserChannelMap;
    }

    public static Map<String, ChannelHandlerContext> getChannelMap() {
        return channelMap;
    }
}
