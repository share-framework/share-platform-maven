package org.andot.share.basic.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.service.MenuService;
import org.andot.share.basic.service.RoleService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.dto.RoleDTO;
import org.andot.share.common.utils.ObjectUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Api(tags = "角色API", description = "角色API")
@RequestMapping("/role")
@RestController
public class RoleController {

    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @ApiOperation("添加数据")
    @PostMapping("")
    public CommonResult add(@RequestBody RoleDTO roleDTO) {
        boolean result = roleService.saveRole(roleDTO);
        if (result) {
            return CommonResult.success(0, "角色添加成功！");
        } else {
            return CommonResult.failed("角色添加失败！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @RequestBody RoleDTO roleDTO) {
        roleDTO.setRoleId(id);
        boolean result = roleService.updateRole(roleDTO);
        if (result) {
            return CommonResult.success(0, "角色更新成功！");
        } else {
            return CommonResult.failed("角色更新失败！");
        }
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        boolean result = roleService.delRoleById(id);
        if (result) {
            return CommonResult.success(0, "角色删除成功！");
        } else {
            return CommonResult.failed("角色删除失败！");
        }
    }

    @ApiOperation("批量删除数据")
    @DeleteMapping("/dels")
    public CommonResult dels(@RequestBody List<Long> ids) {
        return null;
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        RoleDTO result = roleService.getRoleById(id);
        return CommonResult.success(result, "角色信息获取成功！");
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/list")
    public CommonResult getList(RoleDTO roleDto) {
        return null;
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<RoleDTO> rolePage) {
        PageHelper.startPage(rolePage.getPage(), rolePage.getRows());
        String roleName = null;
        if (!ObjectUtil.isNotEmpty(rolePage.getParam())) {
            roleName = rolePage.getParam().getRoleName();
        }
        List<RoleDTO> list = roleService.getRoleList(roleName);
        return CommonPage.restPage(list);
    }

    @ApiOperation("获取角色权限菜单")
    @GetMapping("/menu")
    public CommonResult getPermissionMenu(@RequestParam(value = "appId") Long appId,
                                          @RequestParam(value = "roleCode") String roleCode){
        HashMap map = menuService.getManageMenuList(appId, roleCode);
        return CommonResult.success(map);
    }

    @ApiOperation("设置菜单角色权限")
    @PostMapping("/menu")
    public CommonResult setPermissionMenu(@RequestParam(value = "menuCode") String menuCode,
                                          @RequestParam(value = "roleCode") String roleCode){
        boolean result = roleService.addMenuRolePermission(roleCode, menuCode);
        if (result) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("设置权限失败，请重试！");
        }
    }

    @ApiOperation("删除菜单角色权限")
    @DeleteMapping("/menu")
    public CommonResult delPermissionMenu(@RequestParam(value = "menuCode") String menuCode,
                                          @RequestParam(value = "roleId") String roleCode){
        boolean result = roleService.delMenuRolePermission(roleCode, menuCode);
        if (result) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("设置权限失败，请重试！");
        }
    }
}
