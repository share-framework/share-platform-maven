package org.andot.share.app.line.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 消息对象
 * @author andot
 */
@Setter
@Getter
public class ComLineMessage implements Serializable {
    private ComLineMessageHeader header;
    private ComLineMessageBody body;
    private ComLineMessageFooter footer;
}
