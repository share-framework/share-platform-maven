package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MenuElement extends BaseEntity {
    private Integer menuElementId;
    private Integer menuId;
    private Integer elementId;
}