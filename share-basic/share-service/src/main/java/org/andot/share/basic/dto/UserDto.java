package org.andot.share.basic.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long xNumber;
    private Long appId;
    private String phone;
    private String password;
    private Integer userType;
    private Integer onlineStatus;
    private Integer disabled;
}
