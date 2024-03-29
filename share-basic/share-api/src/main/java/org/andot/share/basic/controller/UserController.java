package org.andot.share.basic.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.entity.XNumberPool;
import org.andot.share.basic.service.XNumberPoolService;
import org.andot.share.core.util.CurrentUserUtil;
import org.andot.share.core.dto.UserDTO;
import org.andot.share.basic.entity.UserDetail;
import org.andot.share.basic.service.MenuService;
import org.andot.share.basic.service.UserService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.core.dto.RoleDTO;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author lucas
 */
@Api(tags = "用户API", description = "用户API")
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private MenuService menuService;
    @Resource
    private XNumberPoolService xNumberPoolService;

    @ApiOperation("更新数据")
    @PutMapping("/{xNumber}")
    public CommonResult update(@PathVariable("xNumber") Long xNumber,
                               @RequestBody UserDTO userDTO) {
        userDTO.setXNumber(xNumber);
        if (userService.updateUserDetail(userDTO)) {
            return CommonResult.success("更新成功");
        } else {
            return CommonResult.failed("更新失败，请重试！");
        }
    }

    @ApiOperation("禁用用户")
    @DeleteMapping("/{xNumber}")
    public CommonResult del(@PathVariable("xNumber") Long xNumber) {
        if (userService.disabledUser(xNumber)) {
            return CommonResult.success("禁用成功");
        } else {
            return CommonResult.failed("禁用失败，请重试！");
        }
    }

    @ApiOperation("批量删除禁用")
    @DeleteMapping("/dels")
    public CommonResult dels(@RequestBody List<Long> ids) {
        return null;
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("/{xNumber}")
    public CommonResult get(@PathVariable("xNumber") Long xNumber) {
        UserDTO user = userService.getUser(CurrentUserUtil.getUserCode());
        return CommonResult.success(user);
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("/info")
    public CommonResult getDetailInfo() {
        UserDetail userDetail = userService.getUserDetail(CurrentUserUtil.getUserCode());
        if (!CollectionUtils.isEmpty(CurrentUserUtil.userDetail().getRoleList())) {
            List<String> roles = CurrentUserUtil.userDetail().getRoleList().stream()
                    .map(RoleDTO::getRoleCode).collect(Collectors.toList());
            userDetail.setPermissions(menuService.getMenuCodeListByRoleCodes(CurrentUserUtil.getAppId(), roles));

        }
        return CommonResult.success(userDetail);
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/list")
    public CommonResult getList(RoleDTO roleDto) {
        return null;
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<UserDTO> userPage) {
        PageHelper.startPage(userPage.getPage(), userPage.getRows());
        List<UserDetail> list = userService.getUserList(userPage.getParam());
        return CommonPage.restPage(list);
    }

    @ApiOperation("生成账号")
    @PostMapping("/product/xnumber")
    public CommonResult productXNumber(Long count){
        if (Objects.isNull(count)) count = 10L;
        List<XNumberPool> xNumberPools = xNumberPoolService.batchAdd(count);
        return CommonResult.success(xNumberPools);
    }
}
