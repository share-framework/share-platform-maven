package org.andot.share.basic.controller;

import org.andot.share.basic.dto.DictTypeDTO;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.entity.DictType;
import org.andot.share.basic.service.DictTypeService;
import org.andot.share.common.response.CommonPage;
import org.andot.share.common.response.CommonResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据字典信息
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/dict/type")
public class DictTypeController {

    @Resource
    private DictTypeService dictTypeService;

    /**
     * 新增字典类型
     */
    @PreAuthorize("@share.hasPermission('dict:type:add')")
    @PostMapping("")
    public CommonResult add(@Validated @RequestBody DictTypeDTO dictTypeDTO) {
        if (dictTypeService.checkDictTypeUnique(dictTypeDTO)) {
            return CommonResult.failed("新增字典'" + dictTypeDTO.getDictName() + "'失败，字典类型已存在");
        }
        return CommonResult.success(dictTypeService.saveDictType(dictTypeDTO));
    }

    /**
     * 修改字典类型
     */
    @PreAuthorize("@share.hasPermission('dict:type:update')")
    @PutMapping
    public CommonResult edit(@Validated @RequestBody DictTypeDTO dictTypeDTO) {
        if (dictTypeService.checkDictTypeUnique(dictTypeDTO)) {
            return CommonResult.failed("修改字典'" + dictTypeDTO.getDictName() + "'失败，字典类型已存在");
        }
        return CommonResult.success(dictTypeService.updateDictType(dictTypeDTO));
    }

    /**
     * 删除字典类型
     */
    @PreAuthorize("@share.hasPermission('dict:type:remove')")
    @DeleteMapping("/{dictIds}")
    public CommonResult remove(@PathVariable List<Long> dictIds) {
        dictTypeService.delDictTypeByIds(dictIds);
        return CommonResult.success();
    }

    /**
     * 查询字典类型详细
     */
    @PreAuthorize("@share.hasPermission('dict:type:query')")
    @GetMapping(value = "/{dictId}")
    public CommonResult getInfo(@PathVariable Long dictId) {
        return CommonResult.success(dictTypeService.getDictTypeById(dictId));
    }

    @PreAuthorize("@share.hasPermission('dict:type:list')")
    @GetMapping("/table")
    public CommonPage list(@Validated @RequestBody PageDTO<DictType> dictTypePageDTO) {
        List<DictType> list = dictTypeService.getDictTypeList(dictTypePageDTO.getParam());
        return CommonPage.restPage(list);
    }

    /**
     * 刷新字典缓存
     */
    @PreAuthorize("@share.hasPermission('dict:type:remove')")
    @DeleteMapping("/refreshCache")
    public CommonResult refreshCache() {
        dictTypeService.resetDictCache();
        return CommonResult.success();
    }

    /**
     * 获取字典选择框列表
     */
    @GetMapping("/options")
    public CommonResult optionselect() {
        List<DictType> dictTypes = dictTypeService.getDictTypeAll();
        return CommonResult.success(dictTypes);
    }
}