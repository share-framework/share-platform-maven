package org.andot.share.basic.service;

import org.andot.share.basic.dto.RoleDTO;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色服务接口
 * 用于角色创建、修改、删除、查询、分配角色
 *
 * @author lucas
 * @date 2020-4-18 11:07:27
 * @since 1.0
 */
public interface RoleService {
    /**
     * 根据角色id获取角色
     *
     * @param roleId
     * @return
     */
    RoleDTO getRoleById(Long roleId);

    /**
     * 根据角色名称查询角色列表
     *
     * @param roleName
     * @return
     */
    List<RoleDTO> getRoleList(String roleName);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    boolean saveRole(RoleDTO role);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    boolean updateRole(RoleDTO role);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    boolean delRoleById(Long roleCode);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    boolean addMenuRolePermission(String roleCode, String menuCode);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    boolean delMenuRolePermission(String roleCode, String menuCode);
}
