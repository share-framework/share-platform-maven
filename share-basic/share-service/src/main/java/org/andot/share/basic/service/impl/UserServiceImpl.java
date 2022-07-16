package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.andot.share.basic.dao.*;
import org.andot.share.basic.dto.MenuPermissionDTO;
import org.andot.share.basic.entity.Role;
import org.andot.share.basic.entity.RoleUser;
import org.andot.share.basic.entity.User;
import org.andot.share.basic.entity.UserDetail;
import org.andot.share.basic.dto.RoleDTO;
import org.andot.share.basic.dto.UserDTO;
import org.andot.share.basic.dto.XUserDetail;
import org.andot.share.basic.service.UserService;
import org.andot.share.common.type.ConstantType;
import org.andot.share.common.utils.GodUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author andot
 */
@Slf4j
@AllArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserMapper userMapper;
    private final UserDeatilMapper userDeatilMapper;
    private final RoleUserMapper roleUserMapper;
    private final RoleMapper roleMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String xNumber) {
        try {
            UserDTO user;
            if (xNumber.length() == 11) {
                user = this.getUser(xNumber);
            } else {
                user = this.getUser(Long.parseLong(xNumber));
            }
            if (GodUtil.isGod(xNumber)) {
                List<RoleDTO> roles = new ArrayList<RoleDTO>(){{
                    add(RoleDTO.builder()
                            .roleCode(ConstantType.GOD_ROLE_CODE)
                            .roleId(1L)
                            .roleName("上帝管理员")
                            .roleType(1).build());
                }};
                List<MenuPermissionDTO> menuPermissionList = menuMapper.getMenuListByRoleCodes(1L, new ArrayList<String>(){{
                    add(ConstantType.GOD_ROLE_CODE);
                }});
                return new XUserDetail(user, roles, menuPermissionList);
            }
            List<RoleUser> roleUserList = roleUserMapper.selectList(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getXNumber, xNumber));
            List<String> roleCodes = roleUserList.stream().map(RoleUser::getRoleCode).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(roleCodes)) {
                LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
                roleLambdaQueryWrapper.in(Role::getRoleCode, roleCodes);
                List<RoleDTO> roles = roleMapper.selectList(roleLambdaQueryWrapper)
                        .stream().map(item -> RoleDTO.builder()
                                .roleCode(item.getRoleCode())
                                .roleId(item.getRoleId())
                                .roleName(item.getRoleName())
                                .roleType(item.getRoleType()).build())
                        .collect(Collectors.toList());
                List<MenuPermissionDTO> menuPermissionList = menuMapper.getMenuListByRoleCodes(1L, roleCodes);
                return new XUserDetail(user, roles, menuPermissionList);
            }
            throw new UsernameNotFoundException("请为用户设置角色！");
        } catch (Exception ex) {
            log.error("考虑用户编号恶意攻击问题，" + ex.getMessage());
            throw new UsernameNotFoundException("用户错误：" + ex.getMessage());
        }
    }

    @Override
    public UserDetail getUserDetail(Long xNumber) {
        return userDeatilMapper.selectOne(new LambdaQueryWrapper<UserDetail>().eq(UserDetail::getXNumber, xNumber));
    }

    @Override
    public UserDTO getUser(Long xNumber) {
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getXNumber, xNumber)), userDto);
        return userDto;
    }

    @Override
    public UserDTO getUser(String phone) {
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone)), userDto);
        return userDto;
    }

    @Override
    public XUserDetail login(String number, String password) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getPhone, number).or().eq(User::getXNumber, number).last("LIMIT 1"));
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(user, userDto);
        // TODO
        userDto.setAppId(1L);
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            List<RoleUser> roleUserList = roleUserMapper.selectList(new LambdaQueryWrapper<RoleUser>().eq(RoleUser::getXNumber, user.getXNumber()));
            List<String> roleCodes = roleUserList.stream().map(RoleUser::getRoleCode).collect(Collectors.toList());
            List<RoleDTO> roles = new ArrayList<>();
            List<MenuPermissionDTO> menuPermissionList = new ArrayList<>();
            if (roleCodes.size() != 0) {
                roles = roleMapper.selectList(new LambdaQueryWrapper<Role>().in(Role::getRoleCode, roleCodes))
                        .stream().map(item -> RoleDTO.builder()
                                .roleCode(item.getRoleCode())
                                .roleId(item.getRoleId())
                                .roleName(item.getRoleName())
                                .roleType(item.getRoleType()).build())
                        .collect(Collectors.toList());
                if (roles.contains(ConstantType.GOD_ROLE_CODE)) {
                    MenuPermissionDTO menuPermissionDTO = new MenuPermissionDTO();
                    menuPermissionDTO.setMenuCode("*:*:*");
                    menuPermissionDTO.setMenuId(0L);
                    menuPermissionList.add(menuPermissionDTO);
                } else {
                    menuPermissionList = menuMapper.getMenuListByRoleCodes(1L, roleCodes);
                }
            }
            return new XUserDetail(userDto, roles, menuPermissionList);
        } else {
            throw new UsernameNotFoundException("用户密码错误");
        }
    }

    @Override
    public boolean updateUserDetail(UserDTO userDTO) {
        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(userDTO, userDetail);
        return userDeatilMapper.updateById(userDetail)>0;
    }

    @Override
    public boolean disabledUser(Long xNumber) {
        User user = new User();
        user.setXNumber(xNumber);
        user.setDisabled(true);
        return userMapper.updateById(user)>0;
    }

    @Override
    public List<UserDetail> getUserList(UserDTO userParam) {
        LambdaQueryWrapper<UserDetail> queryWrapper = new LambdaQueryWrapper<>();
        List<UserDetail> userDetailList = userDeatilMapper.selectList(queryWrapper);
        return userDetailList;
    }
}
