package org.andot.share.basic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色视图实体
 * @author andot
 */
@Setter
@Getter
@Builder
public class RoleDTO {
    private Long roleId;
    private String roleName;
    private String roleCode;
    private Byte roleType;
    private Integer roleOrder;
    private Boolean disabled;
}
