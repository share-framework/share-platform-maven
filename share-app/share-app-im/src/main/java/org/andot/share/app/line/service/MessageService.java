package org.andot.share.app.line.service;

import org.andot.share.app.line.core.domain.ComLineMessage;
import org.andot.share.app.line.core.domain.Room;

import java.util.List;

/**
 * 保存消息
 * 消息记录
 * @date 2022-07-31 15:14:42
 * @since 1.0.0
 *
 */
public interface MessageService {

    /**
     * 是否存在该房间存储消息记录的集合
     *
     */
    boolean isRoomCollection(String collectionName);

    /**
     * 在mongodb里面存储的消息是按照room进行分集合
     * 这样可以降低每个集合的数据量，提高查询速度
     *
     */
    void insertRoomCollection(String collectionName);

    /**
     * room 列表数据
     */
    void insertRoom(Room room);

    /**
     * 插入个人消息
     * @param comLineMessage
     */
    void insertPersonMessage (ComLineMessage comLineMessage);

    /**
     * 根据用户id获取对应消息
     * @param lineId 用户id
     * @return 消息列表
     */
    List<ComLineMessage> getPersonMessageList (String lineId);

    /**
     * 根据聊天室id获取 聊天室的历史聊天记录（分页）
     * @param lineId 用户id
     * @param page 页数
     * @param size 条数
     * @return 消息列表
     */
    List<ComLineMessage> getPersonMessageList (String lineId, Integer page, Integer size);

    /**
     * 根据会话id获取对应消息
     * @param conversationId 会话id
     * @return 消息列表
     */
    List<ComLineMessage> getConversationMessageList (String conversationId);


}
