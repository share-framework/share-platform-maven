package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {
    private Integer roleId;
    private String roleName;
    private String roleCode;
    private Byte roleType;
}