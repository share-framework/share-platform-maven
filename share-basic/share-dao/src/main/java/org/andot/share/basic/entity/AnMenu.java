package org.andot.share.basic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AnMenu extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long menuId;
    private String menuName;
    private String redirect;
    private String component;
    private String menuCode;
    private String menuUrl;
    private Integer menuType;
    private String menuParentCode;
    private String menuIcon;
    private Long appSystemId;
    private Integer orderCode;
}