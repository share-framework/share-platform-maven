package org.andot.share.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.shre.basic.dto.OrganDto;
import org.andot.shre.basic.dto.PageDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * 组织机构控制器
 * @author andot
 * @date 2019-1-17 16:03:09
 * @since 1.0
 */
@Api(tags = "组织机构API", description = "组织机构控制器")
@RequestMapping("/organ")
@RestController
public class OrganController {

    @PostMapping("")
    public CommonResult add(@RequestBody OrganDto organDto) {
        return null;
    }

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
    @GetMapping("/list")
    public CommonResult getList(OrganDto organDto) {
        return null;
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDto<OrganDto> organPage) {
        return null;
    }
}
