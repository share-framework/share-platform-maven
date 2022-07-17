package org.andot.share.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.core.util.CurrentUserUtil;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.core.dto.RoleDTO;
import org.andot.share.core.dto.UserDTO;
import org.andot.share.basic.entity.UserDetail;
import org.andot.share.basic.service.UserService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lucas
 */
@Api(tags = "好友API")
@RequestMapping("/friend")
@RestController
public class FriendController {

    @Resource
    private UserService userService;

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
        return CommonResult.success(userDetail);
    }

    @ApiOperation("根据条件获取好友列表")
    @GetMapping("/list")
    public CommonResult getList(@RequestParam(required = false) String groupId,
                                @RequestParam(required = false) String keyword) {
        UserDTO userDTO = new UserDTO();
        return CommonResult.success(userService.getUserList(userDTO));
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<RoleDTO> rolePage) {
        return null;
    }
}
