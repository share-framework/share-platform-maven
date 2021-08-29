package org.andot.share.common.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

/**
 *
 * @author andot
 */
@Setter
@Getter
@ToString
public class JwtUserDetail {

    private static final long serialVersionUID = 1L;

    private List<String> roles;

    private String xNumber;
    private Long appId;

    private String username;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private List<String> permissions;
}
