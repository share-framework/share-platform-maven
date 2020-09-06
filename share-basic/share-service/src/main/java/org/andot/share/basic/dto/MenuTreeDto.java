package org.andot.share.basic.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单的树形结构
 * @author andot
 */
@ApiModel("菜单的树形结构")
@Setter
@Getter
public class MenuTreeDto {
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单地址")
    private String menuUrl;
    @ApiModelProperty("菜单类型")
    private Integer menuType;
    @ApiModelProperty("菜单父id")
    private Long menuParentId;
    @ApiModelProperty("菜单图标")
    private String menuIcon;
    @ApiModelProperty("菜单名稱")
    private String pageUrl;
    @ApiModelProperty("系统id")
    private Long appSystemId;
    @ApiModelProperty("菜单代码")
    private String orderCode;
    @ApiModelProperty("子菜单列表")
    private List<MenuTreeDto> children;
}
