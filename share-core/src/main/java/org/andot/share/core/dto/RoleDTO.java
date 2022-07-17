package org.andot.share.core.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 角色视图实体
 * @author andot
 */
@Setter
@Getter
@Builder
@ToString
public class RoleDTO {
    private Long roleId;
    private String roleName;
    private String roleCode;
    private Integer roleType;
    private Integer roleOrder;
    private Boolean disabled;
    private String memo;
    private List<String> menuCodes;
    private Boolean menuCheckStrictly;
}
