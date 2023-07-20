package org.andot.share.core.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author andot
 */
@Setter
@Getter
public class UserDTO {
    private Long xNumber;
    private Long appId;
    private String realName;
    private String phone;
    private String email;
    private String password;
    private Integer userType;
    private Integer onlineStatus;
    private Integer disabled;
}
