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
    private String name;
    @ApiModelProperty("菜单id")
    private String id;
    @ApiModelProperty("菜单id")
    private String redirect;
    @ApiModelProperty("菜单id")
    private String component;
    @ApiModelProperty("菜单地址")
    private String url;
    @ApiModelProperty("菜单类型")
    private Integer type;
    @ApiModelProperty("菜单父id")
    private String menuParentCode;
    @ApiModelProperty("菜单图标")
    private String icon;
    @ApiModelProperty("系统id")
    private Long appSystemId;
    @ApiModelProperty("菜单代码")
    private Integer sort;
    @ApiModelProperty("显隐性")
    private Integer disabled;
    @ApiModelProperty("子菜单列表")
    private List<MenuTreeDto> children;
}
