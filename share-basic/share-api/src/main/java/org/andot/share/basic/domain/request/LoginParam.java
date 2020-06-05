package org.andot.share.basic.domain.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginParam {
    private String number;
    private String password;
}
