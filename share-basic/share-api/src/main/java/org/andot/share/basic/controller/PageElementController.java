package org.andot.share.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.entity.MenuElement;
import org.andot.share.basic.entity.PageElement;
import org.andot.share.basic.service.PageElementService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.dto.RoleDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/***
 * 菜单元素控制器
 * @author andot
 * @date 2021-12-12 11:44:02
 * @since 1.0
 */
@Api(tags = "菜单元素API", description = "菜单元素控制器")
@RequestMapping("/page/element")
@RestController
public class PageElementController {

    @Resource
    private PageElementService pageElementService;

    @PostMapping("")
    public CommonResult add(@RequestBody PageElement pageElement) {
        if (pageElementService.savePageElement(pageElement)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody PageElement pageElement) {
        if (pageElementService.updatePageElement(id, pageElement)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
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
    public CommonResult getList(RoleDTO roleDto) {
        return null;
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<RoleDTO> rolePage) {
        return null;
    }
}
