package org.andot.share.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.component.utils.CurrentUserUtil;
import org.andot.share.basic.dto.UserDTO;
import org.andot.share.basic.entity.UserDetail;
import org.andot.share.basic.service.UserService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.dto.RoleDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lucas
 */
@Api(tags = "用户API", description = "用户API")
@RequestMapping("/user")
@RestController
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation("批量删除数据")
    @DeleteMapping("/dels")
    public CommonResult dels(@RequestBody List<Long> ids) {
        return null;
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("")
    public CommonResult get() {
        UserDTO user = userService.getUser(CurrentUserUtil.getUserCode());
        return CommonResult.success(user);
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("/info")
    public CommonResult getDetailInfo() {
        UserDetail userDetail = userService.getUserDetail(CurrentUserUtil.getUserCode());
        return CommonResult.success(userDetail);
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/list")
    public CommonResult getList(RoleDTO roleDto) {
        return null;
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<RoleDTO> rolePage) {
        return null;
    }
}
