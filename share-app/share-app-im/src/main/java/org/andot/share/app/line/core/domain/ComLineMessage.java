package org.andot.share.app.line.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.andot.share.app.line.core.domain.enums.MessageType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息对象
 * @author andot
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
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

    public Long getTime() {
        return this.footer.getTimestamp();
    }

    public ComLineMessage(Integer msgType) {
        this.header = new ComLineMessageHeader();
        this.header.setMsgType(msgType);
    }
}
