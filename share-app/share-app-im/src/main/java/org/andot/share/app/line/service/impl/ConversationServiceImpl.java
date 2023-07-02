package org.andot.share.app.line.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andot.share.app.line.core.domain.Conversation;
import org.andot.share.app.line.mapper.ConversationMapper;
import org.andot.share.app.line.service.ConversationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ConversationServiceImpl
 *
 * @Description TODO
 * @Author lucasser
 * @Date 2023/6/25 22:19
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {

    private final ConversationMapper conversationMapper;

    @Override
    public void save(Conversation conversation) {
        conversationMapper.insert(conversation);
    }

    @Override
    public void updateById(Conversation conversation) {
        conversationMapper.updateById(conversation);
    }

    @Override
    public List<Conversation> getListByXNumber(Long xNumber) {
        return conversationMapper.selectList(new LambdaQueryWrapper<Conversation>().
                eq(Conversation::getOwnerUserId, xNumber));
    }

    @Override
    public Conversation getById(String conversationId) {
        return conversationMapper.selectById(conversationId);
    }

    @Override
    public Conversation getOneByTwoXNumber(Long sendXNumber, Long rcvXNumber) {
        return conversationMapper.selectOne(new LambdaQueryWrapper<Conversation>().
                eq(Conversation::getOwnerUserId, sendXNumber).
                eq(Conversation::getUserId, rcvXNumber));
    }

    @Override
    public void delConversation(String conversationId) {
        conversationMapper.deleteById(conversationId);
    }
}
