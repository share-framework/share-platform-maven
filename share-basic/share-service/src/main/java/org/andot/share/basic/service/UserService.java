package org.andot.share.basic.service;

import org.andot.share.basic.entity.UserDetail;
import org.andot.share.basic.dto.UserDto;
import org.andot.share.basic.dto.XUserDetail;

public interface UserService {
    /**
     * 根据 x number 获取用户信息
     *
     * @param xNumber
     * @return
     */
    UserDetail getUserDetail(Long xNumber);

    UserDto getUser(Long xNumber);

    UserDto getUser(String phone);

    XUserDetail login(String number, String password);
}
