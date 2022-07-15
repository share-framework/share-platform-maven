package org.andot.share.basic.dto;

import lombok.Data;

/**
 * 菜单权限DTO
 * @author lucas
 */
@Data
public class MenuPermissionDTO {
    private Long menuId;
    private String menuName;
    private String menuCode;
    private Long roleMenuId;
    private String menuParentCode;
    private Boolean hidden;
}
