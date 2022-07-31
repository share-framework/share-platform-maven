package org.andot.share.app.line.core.domain;

import lombok.Data;
import org.andot.share.basic.entity.BaseEntity;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * 房间
 */
@Data
public class Room extends BaseEntity {
    /**
     * room id rule
     * LR_id
     */
    @MongoId
    private String roomId;
    /**
     * 房间创建人id
     */
    private String lineId;
    /**
     * 房间名称
     */
    private String roomName;
    /**
     * 房间说明
     */
    private String roomDesc;
    /**
     * 房间类型
     */
    private String roomType;
    /**
     * 房间兴趣类别
     */
    private String roomCategory;
    /**
     * 房间容纳人数
     */
    private Integer roomPersonCount;
    /**
     * 房间限制人数
     */
    private Integer limitPersonCount;
}
