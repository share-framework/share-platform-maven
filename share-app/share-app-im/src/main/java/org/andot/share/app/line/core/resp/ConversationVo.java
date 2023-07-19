package org.andot.share.app.line.core.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * ConversationVo
 *
 * @Description TODO
 * @Author lucasser
 * @Date 2023/7/18 21:19
 * @since 1.0
 */
@Setter
@Getter
@ToString
public class ConversationVo implements Serializable {
    private String conversationId;
    /**
     * 接收人姓名
     */
    private String acceptPersonName;
    private String sendPersonName;
    /**
     * 最后一次会话记录时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "a mm:ss")
    private Date lastConversationTime;
    private String lastConversation;
    private Long ownerUserId;
    private Integer conversationType;
    private Long userId;
}
