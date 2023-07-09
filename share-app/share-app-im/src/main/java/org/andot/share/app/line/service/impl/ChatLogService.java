package org.andot.share.app.line.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andot.share.app.line.core.domain.ChatLog;
import org.andot.share.app.line.mapper.MysqlChatLogMapper;
import org.andot.share.common.response.CommonPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MysqlChatLogService
 *
 * @Description TODO
 * @Author lucasser
 * @Date 2023/7/2 13:13
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service("chatLogService")
public class ChatLogService implements org.andot.share.app.line.service.ChatLogService {

    private final MysqlChatLogMapper chatLogMapper;

    @Override
    public List<ChatLog> getChatLogsByConversationId(String conversationId) {
        List<ChatLog> chatLogs = chatLogMapper.selectList(new LambdaQueryWrapper<ChatLog>().
                eq(ChatLog::getClientMsgId, conversationId).orderByAsc(ChatLog::getSendTime));
        return chatLogs;
    }

    @Override
    public CommonPage<ChatLog> getChatLogsByConversationIdOfPage(String conversationId, Integer page, Integer size) {
        List<ChatLog> chatLogs = chatLogMapper.selectList(new LambdaQueryWrapper<ChatLog>().
                eq(ChatLog::getClientMsgId, conversationId).orderByAsc(ChatLog::getSendTime));
        return CommonPage.restPage(chatLogs);
    }
}
