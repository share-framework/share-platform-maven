package org.andot.share.basic.service;

import org.andot.share.basic.entity.UserDetail;
import org.andot.share.core.dto.UserDTO;
import org.andot.share.core.dto.XUserDetail;

import java.util.List;

public interface UserService {
    /**
     * 根据 x number 获取用户信息
     *
     * @param xNumber
     * @return
     */
    UserDetail getUserDetail(Long xNumber);

    UserDTO getUser(Long xNumber);

    UserDTO getUser(String phone);

    XUserDetail login(String number, String password);

    boolean updateUserDetail(UserDTO userDTO);

    boolean disabledUser(Long xNumber);

    /**
     * 根据条件查询用户列表
     * @param userDTO
     * @return
     */
    List<UserDetail> getUserList(UserDTO userDTO);

    /**
     * 注册添加用户
     * @param userDTO
     * @return
     */
    UserDTO addUserBase(UserDTO userDTO);


}
