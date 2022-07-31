package org.andot.share.app.line.util;

import lombok.RequiredArgsConstructor;
import org.andot.share.app.line.core.domain.ComLineMessage;
import org.andot.share.app.line.service.MessageService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class MessageSaveUtil {
    private final MessageService mongoMessageService;
    private static MessageSaveUtil that;

    /**
     *
     */
    @PostConstruct
    public void init() {
        that = this;
    }

    public static boolean isCollection(String collectionName) {
        return that.mongoMessageService.isRoomCollection(collectionName);
    }

    public static void addCollection(String collectionName) {
        if (that.mongoMessageService.isRoomCollection(collectionName)) {
            that.mongoMessageService.insertRoomCollection(collectionName);
        }
    }

    public static void addMessage(ComLineMessage comLineMessage) {
        that.mongoMessageService.insertPersonMessage(comLineMessage);
    }

}
