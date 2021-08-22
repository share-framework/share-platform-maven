package org.andot.share.core.web;


import org.andot.share.common.cache.LocalThreadCache;
import org.andot.share.common.domain.JwtUserDetail;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * share 自定义权限实现
 *
 * @author andot
 */
@Service("share")
public class PermissionComponent {
    /** 所有权限标识 */
    private static final String ALL_PERMISSION = "*:*:*";

    /** 管理员角色权限标识 */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermission(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        JwtUserDetail userDetail = LocalThreadCache.getUserDetail();
        if (StringUtils.isEmpty(userDetail) || CollectionUtils.isEmpty(userDetail.getPermissions())) {
            return false;
        }
        return hasPermissions(userDetail.getPermissions(), permission);
    }

    /**
     * 验证用户是否不具备某权限，与 hasPermi逻辑相反
     *
     * @param permission 权限字符串
     * @return 用户是否不具备某权限
     */
    public boolean lacksPermissions(String permission) {
        return hasPermission(permission) != true;
    }

    /**
     * 验证用户是否具有以下任意一个权限
     *
     * @param permissions 以 PERMISSION_NAMES_DELIMETER 为分隔符的权限列表
     * @return 用户是否具有以下任意一个权限
     */
    public boolean hasAnyPermissions(String permissions) {
        if (StringUtils.isEmpty(permissions)) {
            return false;
        }
        JwtUserDetail userDetail = LocalThreadCache.getUserDetail();
        if (StringUtils.isEmpty(userDetail) || CollectionUtils.isEmpty(userDetail.getPermissions())) {
            return false;
        }
        List<String> authorities = userDetail.getPermissions();
        for (String permission : permissions.split(PERMISSION_DELIMETER)) {
            if (permission != null && hasPermissions(authorities, permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断用户是否拥有某个角色
     *
     * @param role 角色字符串
     * @return 用户是否具备某角色
     */
    public boolean hasRole(String role) {
        if (StringUtils.isEmpty(role)) {
            return false;
        }
        JwtUserDetail userDetail = LocalThreadCache.getUserDetail();
        if (StringUtils.isEmpty(userDetail) || CollectionUtils.isEmpty(userDetail.getPermissions())) {
            return false;
        }
        for (String roleCode : userDetail.getRoles()) {
            if (SUPER_ADMIN.equalsIgnoreCase(roleCode) || roleCode.equalsIgnoreCase(StringUtils.trimAllWhitespace(role))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证用户是否不具备某角色，与 isRole逻辑相反。
     *
     * @param role 角色名称
     * @return 用户是否不具备某角色
     */
    public boolean lacksRole(String role)
    {
        return hasRole(role) != true;
    }

    /**
     * 验证用户是否具有以下任意一个角色
     *
     * @param roles 以 ROLE_NAMES_DELIMETER 为分隔符的角色列表
     * @return 用户是否具有以下任意一个角色
     */
    public boolean hasAnyRoles(List<String> roles) {
        if (StringUtils.isEmpty(roles)) {
            return false;
        }
        JwtUserDetail userDetail = LocalThreadCache.getUserDetail();
        if (StringUtils.isEmpty(userDetail) || CollectionUtils.isEmpty(userDetail.getPermissions())) {
            return false;
        }
        for (String role : roles) {
            if (hasRole(role)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    private boolean hasPermissions(List<String> permissions, String permission) {
        boolean b = permissions.contains(ALL_PERMISSION) || permissions.contains(StringUtils.trimAllWhitespace(permission));
        LocalThreadCache.remove();
        return b;
    }
}
