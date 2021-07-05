package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author andot
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Action extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long actionId;
    private String actionName;
    private Byte actionType;
    private String actionUrl;
    private Long menuId;
    private String caption;
}