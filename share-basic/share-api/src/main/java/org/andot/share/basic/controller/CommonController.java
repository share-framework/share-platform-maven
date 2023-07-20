package org.andot.share.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.common.components.ShareValueComponent;
import org.andot.share.basic.domain.request.LoginParam;
import org.andot.share.basic.entity.XNumberPool;
import org.andot.share.basic.service.XNumberPoolService;
import org.andot.share.common.type.ConstantType;
import org.andot.share.common.utils.RedisUtil;
import org.andot.share.core.dto.MenuPermissionDTO;
import org.andot.share.common.domain.AccessToken;
import org.andot.share.common.domain.JwtUserDetail;
import org.andot.share.common.response.CommonResult;
import org.andot.share.common.utils.JwtUtil;
import org.andot.share.core.dto.RoleDTO;
import org.andot.share.core.dto.UserDTO;
import org.andot.share.core.dto.XUserDetail;
import org.andot.share.basic.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Andot
 */
@Api(tags = "公共API", description = "操作方法请求控制器")
@RestController
public class CommonController {

    @Resource
    private UserService userService;
    @Resource
    private ShareValueComponent shareValueComponent;
    @Resource
    private XNumberPoolService xNumberPoolService;

    @PostMapping("/login")
    public CommonResult login(@RequestBody LoginParam loginParam,
                              HttpServletRequest request) {
        XUserDetail userDetail = userService.login(loginParam.getNumber(), loginParam.getPassword());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetail.getUsername(), userDetail.getPassword(), userDetail.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        AccessToken accessToken = new AccessToken();
        accessToken.setPrefix("Bearer");
        JwtUserDetail jwtUserDetail = new JwtUserDetail();
        jwtUserDetail.setRoles(userDetail.getRoleList().stream().map(RoleDTO::getRoleCode).collect(Collectors.toList()));
        jwtUserDetail.setUsername(userDetail.getUser().getPhone());
        jwtUserDetail.setXNumber(userDetail.getUsername());
        jwtUserDetail.setAppId(userDetail.getUser().getAppId());
        jwtUserDetail.setPermissions(userDetail.getMenuDTOList().stream().map(MenuPermissionDTO::getMenuCode).collect(Collectors.toList()));
        jwtUserDetail.setRealName(userDetail.getUsername());
        accessToken.setData(jwtUserDetail);
        String token = JwtUtil.productJwtToken(jwtUserDetail, shareValueComponent.getJwtSecret(), shareValueComponent.getJwtExpiration());
        String token2 = JwtUtil.productJwtToken2(jwtUserDetail, shareValueComponent.getJwtSecret(), shareValueComponent.getJwtExpiration());
        accessToken.setToken(token);
        RedisUtil.put(token, token2, 24*60*60L);
        return CommonResult.success(accessToken, "登录成功");
    }

    @DeleteMapping("/logout")
    public CommonResult logout() {

        return CommonResult.success( "退出成功");
    }

    /**
     * 注册账号
     * @return
     */
    @PostMapping("/{type}/sign")
    public CommonResult sign(@PathVariable("type") String type, @RequestBody UserDTO userDTO) {
        int count = 10;
        List<XNumberPool> xNumberPoolList = xNumberPoolService.getXNumberPool(count);
        Random random = new Random();
        if (ConstantType.PHONE.equalsIgnoreCase(type)) {
            XNumberPool xNumberPool = xNumberPoolList.get(random.nextInt(count));
            userDTO.setXNumber(xNumberPool.getXNumber());
            userDTO.setAppId(1L);
            UserDTO userDTO1 = userService.addUserBase(userDTO);
            return CommonResult.success(userDTO1);
        }
        return null;
    }

    /**
     * 健康检查
     * @return
     */
    @GetMapping("/healthy/status")
    public CommonResult healthy() {
        return CommonResult.success("basic api startup sucess!");
    }

    /**
     * 刷新token，token续期
     * @param token
     * @return
     */
    @ApiOperation("刷新token，token续期")
    @GetMapping("/refreshToken")
    public CommonResult refush(@RequestHeader("X-Token") String token) {
        if (!JwtUtil.isTokenExpired(token)) {
            return CommonResult.success(token);
        }
        String tokenE = RedisUtil.get(token);
        if (StringUtils.isNotBlank(tokenE)) {
            JwtUserDetail jwtUserDetail = JwtUtil.releaseJwtToken(tokenE, shareValueComponent.getJwtSecret());
            String newToken = JwtUtil.productJwtToken(jwtUserDetail, shareValueComponent.getJwtSecret(), shareValueComponent.getJwtExpiration());
            RedisUtil.put(token, newToken, 24*60*60L);
            return CommonResult.success(newToken);
        }
        return CommonResult.failed();
    }
}
