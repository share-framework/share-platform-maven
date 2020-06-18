package org.andot.share.basic.service.impl;

import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.RoleMapper;
import org.andot.share.basic.entity.Role;
import org.andot.share.basic.dto.RoleDto;
import org.andot.share.basic.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @see org.andot.share.basic.service.RoleService
 */
@AllArgsConstructor
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public RoleDto getRoleById(Long roleId) {
        Role role = roleMapper.selectById(roleId);
        RoleDto roleDto = RoleDto.builder().build();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }

    @Override
    public List<RoleDto> getRoleList(String roleName) {
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public boolean saveRole(RoleDto role) {
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public boolean updateRole(RoleDto role) {
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    @Override
    public boolean delRoleById(Long id) {
        return false;
    }
}
