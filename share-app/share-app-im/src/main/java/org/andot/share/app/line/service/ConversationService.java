package org.andot.share.app.line.service;

import org.andot.share.app.line.core.domain.Conversation;

import java.util.List;

/**
 * ConversationService
 *
 * @Description TODO
 * @Author lucasser
 * @Date 2023/6/25 22:19
 * @since 1.0
 */
public interface ConversationService {

    void save(Conversation conversation);

    void updateById(Conversation conversation);

    List<Conversation> getListByXNumber(Long xNumber);


    Conversation getById(String conversationId);

    /**
     * 根据会话发起人和接收人拉取单个会话
     * @param sendXNumber
     * @param rcvXNumber 接收人
     * @return
     */
    Conversation getOneByTwoXNumber(Long sendXNumber, Long rcvXNumber);

    /**
     *
     * @param conversationId
     */
    void delConversation(String conversationId);
}
