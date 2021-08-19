package org.andot.share.basic.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author andot
 */
@Setter
@Getter
@ToString
public class RoleUser {
    private Long roleUserId;
    private String roleCode;
    private Long xNumber;
}
