package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuOperatePower extends BaseEntity {
    private Integer menuPowerId;
    private Integer powerId;
    private Integer menuId;
}