package org.andot.share.app.line.core.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * Conversation
 *
 * @Description TODO
 * @Author lucasser
 * @Date 2023/6/25 22:08
 * @since 1.0
 */
@Data
public class Conversation implements Serializable {
    @TableId(type = IdType.INPUT)
    private String conversationId;
    private Long ownerUserId;
    private Integer conversationType;
    private Long userId;
    private String groupId;
    private Integer recvMsgOpt;
    private Integer unreadCount;
    private Long draftTextTime;
    private Boolean isPinned;
    private Boolean isPrivateChat;
    private Integer burnDuration;
    private Integer groupAtType;
    private Boolean isNotInGroup;
}

