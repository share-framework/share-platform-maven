package org.andot.share.app.line.core.domain.enums;

import lombok.Getter;

/**
 * MessageType
 *
 * @Description 1 文本消息 2 图片消息 3 语音消息 4 视频消息 5 文件消息 6 定位消息
 * @Author lucasser
 * @Date 2023/7/2 13:39
 * @since 1.0
 */
@Getter
public enum MessageType {

    TEXT(1, "文本消息"),
    IMAGE(2, "图片消息"),
    VOICE(3, "语音消息"),
    VIDEO(4, "视频消息"),
    FILE(5, "文件消息"),
    POSITION(6, "定位消息"),
    HEART(7, "心跳消息"),
    TOKEN_EXPIRED(8, "token过期消息"),
    OTHER(0, "未知消息");

    private int code;
    private String desc;

    MessageType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
