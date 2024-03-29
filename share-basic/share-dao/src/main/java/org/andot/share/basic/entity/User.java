package org.andot.share.basic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author andot
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {
    private Long userId;
    private Long xNumber;
    private String phone;
    private String email;
    private String password;
    private String salt;
    private Integer userType;
    private Integer onlineStatus;
}
