package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.andot.share.basic.dao.DictDataMapper;
import org.andot.share.basic.dao.DictTypeMapper;
import org.andot.share.basic.dto.DictTypeDTO;
import org.andot.share.basic.entity.DictData;
import org.andot.share.basic.entity.DictType;
import org.andot.share.basic.service.DictDataService;
import org.andot.share.basic.service.DictTypeService;
import org.andot.share.basic.utils.DictUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andot
 * @see org.andot.share.basic.service.DictTypeService
 */
@RequiredArgsConstructor
@Service("dictTypeService")
public class DictTypeServiceImpl implements DictTypeService {

    private final DictTypeMapper dictTypeMapper;
    private final DictDataService dictDataService;

    @Override
    public void loadingDictCache() {

    }

    @Override
    public void clearDictCache() {

    }

    @Override
    public void resetDictCache() {
        DictUtil.clearDictCache();
        List<DictType> dictTypeList = getDictTypeAll();
        for (DictType dictType : dictTypeList) {
            List<DictData> dictDataList = dictDataService.getDictDataByType(dictType.getDictType());
            DictUtil.setDictCache(dictType.getDictType(), dictDataList);
        }
    }

    @Override
    public List<DictType> getDictTypeList(DictType dictType) {
        LambdaQueryWrapper<DictType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DictType::getDisabled, false);
        if (!StringUtils.isEmpty(dictType.getDictName())) {
            lambdaQueryWrapper.like(DictType::getDictName, dictType.getDictName());
        }
        if (!StringUtils.isEmpty(dictType.getDictType())) {
            lambdaQueryWrapper.eq(DictType::getDictType, dictType.getDictType());
        }
        return dictTypeMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Boolean checkDictTypeUnique(DictTypeDTO dictTypeDTO) {
        LambdaQueryWrapper<DictType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DictType::getDisabled, false);
        lambdaQueryWrapper.eq(DictType::getDictType, dictTypeDTO.getDictType());
        return dictTypeMapper.selectCount(lambdaQueryWrapper)>1;
    }

    @Override
    public DictType getDictTypeById(Long dictTypeId) {
        return dictTypeMapper.selectById(dictTypeId);
    }

    @Override
    public boolean saveDictType(DictTypeDTO dictTypeDTO) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(dictTypeDTO, dictType);
        return dictTypeMapper.insert(dictType)>0;
    }

    @Override
    public boolean updateDictType(DictTypeDTO dictTypeDTO) {
        DictType dictType = new DictType();
        BeanUtils.copyProperties(dictTypeDTO, dictType);
        return dictTypeMapper.updateById(dictType)>0;
    }

    @Override
    public boolean delDictTypeById(Long id) {
        return dictTypeMapper.deleteById(id)>0;
    }

    @Override
    public List<Long> delDictTypeByIds(List<Long> ids) {
        List<Long> notDelIds = new ArrayList<>();
        for (Long id : ids) {
            if (!delDictTypeById(id)) {
                notDelIds.add(id);
            }
        }
        return notDelIds;
    }

    @Override
    public List<DictType> getDictTypeAll() {
        LambdaQueryWrapper<DictType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DictType::getDisabled, false);
        return dictTypeMapper.selectList(lambdaQueryWrapper);
    }
}
