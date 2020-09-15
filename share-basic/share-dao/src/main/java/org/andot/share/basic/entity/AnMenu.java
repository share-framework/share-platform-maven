package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AnMenu extends BaseEntity {
    private Long menuId;
    private String menuName;
    private String redirect;
    private String component;
    private String menuCode;
    private String menuUrl;
    private Integer menuType;
    private String parentCode;
    private String menuIcon;
    private Long appSystemId;
    private Integer orderCode;
}