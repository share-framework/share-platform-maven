package org.andot.share.basic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RoleDto {
    private Integer roleId;
    private String roleName;
    private String roleCode;
    private Byte roleType;
}