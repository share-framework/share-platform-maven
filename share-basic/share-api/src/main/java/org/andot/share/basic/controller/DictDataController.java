package org.andot.share.basic.controller;

import io.jsonwebtoken.lang.Collections;
import io.swagger.annotations.ApiOperation;
import org.andot.share.basic.annotation.PageStart;
import org.andot.share.basic.dto.DictDataDTO;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.entity.DictData;
import org.andot.share.basic.entity.DictData;
import org.andot.share.basic.service.DictDataService;
import org.andot.share.basic.service.DictDataService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/dict/data")
public class DictDataController {

    @Resource
    private DictDataService dictDataService;

    @ApiOperation("添加数据")
    @PreAuthorize("@share.hasPermission('dict:data:add')")
    @PutMapping("")
    public CommonResult save(@Validated @RequestBody DictDataDTO dictDataDTO) {
        if(dictDataService.saveDictData(dictDataDTO)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("添加失败，请重试！");
        }
    }

    @ApiOperation("更新数据")
    @PreAuthorize("@share.hasPermission('dict:data:update')")
    @PutMapping("/{id}")
    public CommonResult update(@Validated @PathVariable("id") Long id,
                               @RequestBody DictDataDTO dictTypeDTO) {
        if(dictDataService.updateDictData(dictTypeDTO)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("保存失败，请重试！");
        }
    }

    @ApiOperation("删除数据")
    @PreAuthorize("@share.hasPermission('dict:data:delete')")
    @DeleteMapping("/{id}")
    public CommonResult del(@PathVariable("id") Long id) {
        if(dictDataService.delDictDataById(id)) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("删除失败，请重试！");
        }
    }

    @ApiOperation("批量删除数据")
    @PreAuthorize("@share.hasPermission('dict:data:delete')")
    @DeleteMapping("/dels")
    public CommonResult dels(@RequestBody List<Long> ids) {
        List<Long> idsNo = dictDataService.delDictDataByIdBatch(ids);
        if(Collections.isEmpty(idsNo)) {
            return CommonResult.success();
        } else {
            return CommonResult.success(idsNo, "有部分删除失败，请重试没有删除的！");
        }
    }

    @ApiOperation("根据id获取数据")
    @PreAuthorize("@share.hasPermission('dict:data:see')")
    @GetMapping("/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return CommonResult.success(dictDataService.getDictDataById(id));
    }

    @ApiOperation("根据条件获取列表数据")
    @PreAuthorize("@share.hasPermission('dict:data:list')")
    @GetMapping(value = "/type/{dictType}")
    public CommonResult dictType(@PathVariable String dictType) {
        List<DictData> data = dictDataService.getDictDataByType(dictType);
        if (StringUtils.isEmpty(data)) {
            data = new ArrayList<DictData>();
        }
        return CommonResult.success(data);
    }

    @ApiOperation("根据条件获取分页列表数据")
    @PreAuthorize("@share.hasPermission('dict:data:table')")
    @PageStart
    @PostMapping("/table")
    public CommonPage getPageList(@RequestBody PageDTO<DictData> dictDataPageDTO) {
        List<DictData> list = dictDataService.getDictDataList(dictDataPageDTO.getParam());
        return CommonPage.restPage(list);
    }
}