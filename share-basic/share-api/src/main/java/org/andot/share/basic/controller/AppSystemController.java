package org.andot.share.basic.controller;

import com.github.pagehelper.PageHelper;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.dto.AppSystemDTO;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.entity.PageElement;
import org.andot.share.basic.service.AppSystemService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/app/system")
@RestController
public class AppSystemController {

    @Resource
    private AppSystemService appSystemService;

    @ApiOperation("添加数据")
    @PutMapping("")
    public CommonResult add(@RequestBody AppSystemDTO appSystemDTO) {
        if (appSystemService.saveAppSystem(appSystemDTO)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable("id") Long id,
                               @RequestBody AppSystemDTO appSystemDTO) {
        appSystemDTO.setAppSystemId(id);
        if (appSystemService.updateAppSystem(appSystemDTO)) {
            return CommonResult.success("保存成功");
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("删除数据")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        if (appSystemService.delAppSystemById(id)) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败，请重试！");
        }
    }

    @ApiOperation("根据id获取数据")
    @GetMapping("/{id}")
    public CommonResult<AppSystemDTO> get(@PathVariable("id") Long id) {
        return CommonResult.success(appSystemService.getAppSystemById(id));
    }

    @ApiOperation("根据条件获取列表数据")
    @GetMapping("/page")
    public CommonPage getList(PageDTO<AppSystemDTO> pageDTO) {
        PageHelper.startPage(pageDTO.getPage(), pageDTO.getRows());
        return CommonPage.restPage(appSystemService.
                getAppSystemList(pageDTO.getParam().getAppSystemName(),
                        pageDTO.getParam().getAppSystemUrl()));
    }

}