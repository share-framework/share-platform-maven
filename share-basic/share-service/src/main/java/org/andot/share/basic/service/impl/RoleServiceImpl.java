package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.andot.share.basic.dao.RoleMapper;
import org.andot.share.basic.dao.RoleMenuMapper;
import org.andot.share.basic.entity.Role;
import org.andot.share.basic.dto.RoleDTO;
import org.andot.share.basic.entity.RoleMenu;
import org.andot.share.basic.service.RoleService;
import org.andot.share.common.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see org.andot.share.basic.service.RoleService
 */
@AllArgsConstructor
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public RoleDTO getRoleById(Long roleId) {
        Role role = roleMapper.selectById(roleId);
        RoleDTO roleDto = RoleDTO.builder().build();
        BeanUtils.copyProperties(role, roleDto);
        return roleDto;
    }

    @Override
    public List<RoleDTO> getRoleList(String roleName) {
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        if (ObjectUtil.isNotEmpty(roleName)) {
           queryWrapper.like(Role::getRoleName, roleName);
        }
        return roleMapper.selectList(queryWrapper).stream().map(role -> RoleDTO.builder()
                .roleCode(role.getRoleCode())
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .roleType(role.getRoleType())
                .disabled(role.getDisabled())
                .build()).collect(Collectors.toList());
    }

    @Override
    public boolean saveRole(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return roleMapper.insert(role)>0;
    }

    @Override
    public boolean updateRole(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return roleMapper.updateById(role)>0;
    }

    @SneakyThrows
    @Override
    public boolean delRoleById(Long id) {
        if (id == 1) {
           throw new OperationNotSupportedException("超级管理员不允许删除！");
        }
        return roleMapper.deleteById(id)>0;
    }

    @Override
    public boolean addMenuRolePermission(Long roleId, String menuCode) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setMenuCode(menuCode);
        roleMenu.setRoleId(roleId);
        return roleMenuMapper.insert(roleMenu)>0;
    }

    @Override
    public boolean delMenuRolePermission(Long roleId, String menuCode) {
        return roleMenuMapper.delete(new LambdaQueryWrapper<RoleMenu>()
                .eq(RoleMenu::getMenuCode, menuCode)
                .eq(RoleMenu::getRoleId, roleId))>0;
    }
}
