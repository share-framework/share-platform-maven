package org.andot.share.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.dto.RoleDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 组织机构控制器
 * @author andot
 * @date 2019-1-17 16:03:09
 * @since 1.0
 */
@Api(tags = "组织机构API", description = "组织机构控制器")
@RequestMapping("/menu/element")
@RestController
public class MenuElementController {

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
    @GetMapping("/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return null;
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/all")
    public CommonResult getList(RoleDTO roleDto) {

        return null;
    }
}
