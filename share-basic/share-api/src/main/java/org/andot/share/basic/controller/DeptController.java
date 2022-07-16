package org.andot.share.basic.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.dto.DeptDTO;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.service.DeptService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/***
 * 部门控制器
 * @author andot
 * @date 2022-07-16 23:04:23
 * @since 1.0
 */
@Api(tags = "部门API", description = "部门控制器")
@RequestMapping("/dept")
@RestController
public class DeptController {

    @Resource
    private DeptService deptService;

    @PostMapping("")
    public CommonResult add(@RequestBody DeptDTO deptDto) {
        if (deptService.saveDept(deptDto)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @RequestBody DeptDTO deptDto) {
        deptDto.setDeptId(id);
        if (deptService.updateDept(deptDto)) {
            return CommonResult.success("更新成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        if (deptService.delDeptById(id)) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
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
        return CommonResult.success(deptService.getDeptById(id));
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/list")
    public CommonResult getList(DeptDTO deptDto) {
        return CommonResult.success(deptService.getDeptTree(deptDto.getDeptName(), deptDto.getDeptParentCode()));
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<DeptDTO> deptPage) {
        PageHelper.startPage(deptPage.getPage(), deptPage.getRows());
        return CommonPage.restPage(deptService.getDeptList(
                deptPage.getParam().getDeptName(), deptPage.getParam().getDeptParentCode()));
    }
}
