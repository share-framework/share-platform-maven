package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Organ extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long organId;
    private String organName;
    private Integer organType;
    private Integer orderCode;
    private String organUrl;
    private String organCode;
    private String organParentCode;
    private String caption;
    private Integer isDel = 0;
}