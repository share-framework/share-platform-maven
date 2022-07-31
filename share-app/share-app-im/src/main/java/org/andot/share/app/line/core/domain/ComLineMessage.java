package org.andot.share.app.line.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

/**
 * 消息对象
 * @author andot
 */
@Setter
@Getter
@ToString
public class ComLineMessage implements Serializable {
    @MongoId
    private String id;
    /**
     * 聊天室id
     */
    private String roomId;
    private ComLineMessageHeader header;
    private ComLineMessageBody body;
    private ComLineMessageFooter footer;
}
