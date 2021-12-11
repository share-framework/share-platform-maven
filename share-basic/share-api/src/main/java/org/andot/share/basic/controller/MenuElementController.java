package org.andot.share.basic.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.entity.MenuElement;
import org.andot.share.basic.service.MenuElementService;
import org.andot.share.common.response.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @Resource
    private MenuElementService menuElementService;

    @PostMapping("")
    public CommonResult add(@RequestBody MenuElement menuElement) {
        if (menuElementService.saveMenuElement(menuElement)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id, @RequestBody MenuElement menuElement) {
        if (menuElementService.updateMenuElement(id, menuElement)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        if (menuElementService.delMenuElementById(id)) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败，请重试！");
        }
    }

    @ApiOperation("批量删除数据")
    @DeleteMapping("/dels")
    public CommonResult dels(@RequestBody List<Long> ids) {
        if (menuElementService.delBatchMenuElementById(ids)) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败，请重试！");
        }
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return CommonResult.success(menuElementService.getMenuElementList(id));
    }

}
