package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ElementOperatePower extends BaseEntity {
    private Integer elementPowerId;
    private Integer operatePowerId;
    private Integer pageElementId;
}