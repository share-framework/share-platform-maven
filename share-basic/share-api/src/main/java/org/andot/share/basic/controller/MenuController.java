package org.andot.share.basic.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.component.utils.CurrentUserUtil;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.basic.dto.MenuDTO;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * @author lucas
 */
@Api(tags = "菜单API", description = "菜单请求控制器")
@RequestMapping("/menu")
@RestController
public class MenuController {

    @Resource
    private MenuService menuService;

    @PostMapping("")
    public CommonResult add(@RequestBody MenuDTO menuDto) {
        if (menuService.saveMenu(menuDto)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @RequestBody MenuDTO menuDto) {
        if (menuService.updateMenu(id, menuDto)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        if (menuService.delMenuById(id)) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败，请重试！");
        }
    }

    @ApiOperation("批量删除数据")
    @DeleteMapping("/dels")
    public CommonResult dels(@RequestBody List<Long> ids) {
        if (menuService.delBatchMenuById(ids)) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败，请重试！");
        }
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("/{id}")
    public CommonResult<MenuDTO> get(@PathVariable("id") Long id) {
        return CommonResult.success(menuService.getMenuInfoById(id));
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/list")
    public CommonResult<List<MenuDTO>> getList(MenuDTO menuDto) {
        return CommonResult.success(menuService.getMenuList(menuDto.getMenuName(), menuDto.getMenuUrl()));
    }

    @ApiOperation("根据条件获取列表数据 - 下拉框")
    @GetMapping("/selector/list")
    public CommonResult<Map<String, String>> getSelectorList(MenuDTO menuDto) {
        Map<String, String> menuMap = menuService.getMenuList(menuDto.getMenuName(), menuDto.getMenuUrl())
                .stream().collect(Collectors.toMap(MenuDTO::getMenuCode, MenuDTO::getMenuName));
        return CommonResult.success(menuMap);
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<MenuDTO> menuPage) {
        PageHelper.startPage(menuPage.getPage(), menuPage.getRows());
        return CommonPage.restPage(menuService.getMenuList(menuPage.getParam().getMenuName(),
                menuPage.getParam().getMenuUrl()));
    }

    @ApiOperation("加载树形菜单")
    @GetMapping("/tree")
    public CommonResult getTreeList() {
        return CommonResult.success(menuService.getMenuTreeList(CurrentUserUtil.getAppId(),
                CurrentUserUtil.getUserCode()));
    }

    @ApiOperation("管理端-加载树形菜单")
    @GetMapping("/manage/tree")
    public CommonResult getManageTreeList(@RequestParam Long appId) {
        return CommonResult.success(menuService.getManageMenuTreeList(appId));
    }
}