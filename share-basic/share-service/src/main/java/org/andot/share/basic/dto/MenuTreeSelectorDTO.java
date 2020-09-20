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
public class MenuTreeSelectorDTO {
    private Long menuId;
    @ApiModelProperty("菜单名称")
    private String menuName;
    @ApiModelProperty("菜单code")
    private String menuCode;
    @ApiModelProperty("角色菜单关系id 不为0，表示选中")
    private Long roleMenuId;
    @ApiModelProperty("是否存在子节点")
    private Boolean hashChildren;
    @ApiModelProperty("子菜单列表")
    private List<MenuTreeSelectorDTO> children;
}
