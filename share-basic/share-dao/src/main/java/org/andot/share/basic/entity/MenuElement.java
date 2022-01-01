package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单的元素表
 * @author andot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuElement extends BaseEntity {
    private Long menuElementId;
    private Long menuId;
    private Long elementId;

}