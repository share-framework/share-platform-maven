package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Organ extends BaseEntity {
    private Long organId;
    private String organName;
    private Byte organType;
    private Byte orderCode;
    private Integer organParentId;
    private String caption;
}