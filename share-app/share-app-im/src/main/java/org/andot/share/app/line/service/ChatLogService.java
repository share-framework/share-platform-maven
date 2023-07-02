package org.andot.share.app.line.service;

import org.andot.share.app.line.core.domain.ChatLog;
import org.andot.share.common.response.CommonPage;

import java.util.List;

/**
 * ChatLogService
 *
 * @Description TODO
 * @Author lucasser
 * @Date 2023/7/2 13:10
 * @since 1.0
 */
public interface ChatLogService {
    /**
     *
     * @param conversationId 会话id
     * @return 按照时间正序，默认
     */
    List<ChatLog> getChatLogsByConversationId(String conversationId);

    /**
     *
     * @param conversationId 会话id
     * @return 按照时间正序，默认分页 每页20条
     */
    CommonPage<ChatLog> getChatLogsByConversationIdOfPage(String conversationId, Integer page, Integer size);
}
