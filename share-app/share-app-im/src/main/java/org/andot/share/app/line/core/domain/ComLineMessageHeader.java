package org.andot.share.app.line.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 消息对象
 * @author andot
 */
@Setter
@Getter
public class ComLineMessageHeader implements Serializable {
    /**
     * 当前消息发送人id
     */
    private String lineId;
    /**
     * 当前接收消息人id
     */
    private String toLineId;
    /**
     * 消息类型
     * 1 文本消息 2 图片消息 3 语音消息 4 视频消息 5 文件消息 6 定位消息
     */
    private Integer msgType;
}
