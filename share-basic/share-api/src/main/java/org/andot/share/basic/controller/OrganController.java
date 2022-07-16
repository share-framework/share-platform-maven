package org.andot.share.basic.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.service.OrganService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.andot.share.basic.dto.OrganDTO;
import org.andot.share.basic.dto.PageDTO;
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
@RequestMapping("/organ")
@RestController
public class OrganController {

    @Resource
    private OrganService organService;

    @PostMapping("")
    public CommonResult add(@RequestBody OrganDTO organDto) {
        if (organService.saveOrgan(organDto)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @RequestBody OrganDTO organDto) {
        organDto.setOrganId(id);
        if (organService.updateOrgan(organDto)) {
            return CommonResult.success("更新成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        if (organService.delOrganById(id)) {
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
        return CommonResult.success(organService.getOrganById(id));
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/list")
    public CommonResult getList(OrganDTO organDto) {
        return CommonResult.success(organService.getOrganTree(organDto.getOrganName(), organDto.getOrganParentCode()));
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<OrganDTO> organPage) {
        PageHelper.startPage(organPage.getPage(), organPage.getRows());
        return CommonPage.restPage(organService.getOrganList(
                organPage.getParam().getOrganName(), organPage.getParam().getOrganParentCode()));
    }
}
