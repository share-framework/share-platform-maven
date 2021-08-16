package org.andot.share.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("菜單對象")
@Setter
@Getter
public class MenuDTO {
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单id")
    private String menuCode;
    @ApiModelProperty("菜单id")
    private String redirect;
    @ApiModelProperty("菜单id")
    private String component;
    @ApiModelProperty("菜单地址")
    private String menuUrl;
    @ApiModelProperty("菜单类型")
    private Integer menuType;
    @ApiModelProperty("菜单父id")
    private String menuParentCode;
    @ApiModelProperty("菜单图标")
    private String menuIcon;
    @ApiModelProperty("系统id")
    private Long appSystemId;
    @ApiModelProperty("菜单代码")
    private Integer orderCode;
    @ApiModelProperty("显隐性 0 正常 1 禁用")
    private Boolean disabled;
}
