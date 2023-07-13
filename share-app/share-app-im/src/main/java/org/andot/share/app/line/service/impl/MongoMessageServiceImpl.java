package org.andot.share.app.line.service.impl;

import com.alicp.jetcache.anno.Cached;
import com.mongodb.client.MongoCollection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andot.share.app.line.core.domain.ComLineMessage;
import org.andot.share.app.line.core.domain.Room;
import org.andot.share.app.line.service.MessageService;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 保存消息, mongo 实现类
 * 消息记录
 * @date 2022-07-31 15:14:42
 * @since 1.0.0
 *
 */
@RequiredArgsConstructor
@Slf4j
@Service("mongoMessageService")
public class MongoMessageServiceImpl implements MessageService {

    private final MongoTemplate mongoTemplate;

    @Cached(name = "LINE_", key = "IsRoomCollection:{collectionName}")
    @Override
    public boolean isRoomCollection(String collectionName) {
        return mongoTemplate.collectionExists(collectionName);
    }

    @Override
    public void insertRoomCollection(String collectionName) {
        MongoCollection<Document> collection = mongoTemplate.createCollection(collectionName);
        if (Objects.isNull(collection)) {
            throw new RuntimeException("保存消息数据集出错，请重试！");
        }
    }

    @Override
    public void insertRoom(Room room) {

    }

    @Override
    public void insertPersonMessage(ComLineMessage comLineMessage) {
        ComLineMessage result = mongoTemplate.insert(comLineMessage, String.format("msg_%s", comLineMessage.getRoomId()));
        log.info(result.toString());
    }

    @Override
    public List<ComLineMessage> getPersonMessageList(String lineId) {
        return null;
    }

    @Override
    public List<ComLineMessage> getPersonMessageList(String lineId, Integer page, Integer size) {
        return null;
    }

    @Override
    public List<ComLineMessage> getConversationMessageList(String conversationId) {
        String[] cs = conversationId.split("_");
        List<ComLineMessage> clm1 = mongoTemplate.findAll(ComLineMessage.class, String.format("msg_%s", conversationId));
        List<ComLineMessage> clm2 =  mongoTemplate.findAll(ComLineMessage.class, String.format("msg_%s_%s_%s", cs[0], cs[2], cs[1]));
        clm1.addAll(clm2);
        clm1 = clm1.stream().sorted(Comparator.comparing(ComLineMessage::getTime)).collect(Collectors.toList());
        return clm1;
    }
}
