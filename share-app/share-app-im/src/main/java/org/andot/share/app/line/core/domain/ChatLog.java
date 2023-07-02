package org.andot.share.app.line.core.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * ChatLog
 *
 * @Description TODO
 * @Author lucasser
 * @Date 2023/7/2 12:26
 * @since 1.0
 */

@Data
public class ChatLog {
    @TableId(type = IdType.ASSIGN_UUID)
    private String serverMsgID;
    private String clientMsgID;
    private String sendID;
    private String recvID;
    private int senderPlatformID;
    private String senderNickname;
    private String senderFaceURL;
    private int sessionType;
    private int msgFrom;
    private int contentType;
    private String content;
    private int status;
    private Date sendTime;
    private Date createTime;
    private String ex;
}

