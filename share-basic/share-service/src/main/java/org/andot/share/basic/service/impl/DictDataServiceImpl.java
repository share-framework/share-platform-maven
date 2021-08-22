package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.andot.share.basic.dao.DictDataMapper;
import org.andot.share.basic.dto.DictDataDTO;
import org.andot.share.basic.entity.DictData;
import org.andot.share.basic.entity.DictType;
import org.andot.share.basic.service.DictDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andot
 */
@RequiredArgsConstructor
@Service("dictDataService")
public class DictDataServiceImpl implements DictDataService {

    private final DictDataMapper dictDataMapper;

    @Override
    public List<DictData> getDictDataList(DictData dictData) {
        LambdaQueryWrapper<DictData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DictData::getDisabled, false);
        if (StringUtils.isEmpty(dictData.getDictType())) {
            lambdaQueryWrapper.like(DictData::getDictType, dictData.getDictType());
        }
        if (StringUtils.isEmpty(dictData.getDictCode())) {
            lambdaQueryWrapper.like(DictData::getDictCode, dictData.getDictCode());
        }
        if (StringUtils.isEmpty(dictData.getDictLabel())) {
            lambdaQueryWrapper.like(DictData::getDictLabel, dictData.getDictLabel());
        }
        if (StringUtils.isEmpty(dictData.getDictValue())) {
            lambdaQueryWrapper.like(DictData::getDictValue, dictData.getDictValue());
        }
        return dictDataMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public List<DictData> getDictDataByType(String dictType) {
        LambdaQueryWrapper<DictData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DictData::getDisabled, false);
        lambdaQueryWrapper.eq(DictData::getDictType, dictType);
        return dictDataMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public boolean saveDictData(DictDataDTO dictDataDTO) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataDTO, dictData);
        return dictDataMapper.insert(dictData) > 0;
    }

    @Override
    public boolean updateDictData(DictDataDTO dictDataDTO) {
        DictData dictData = new DictData();
        BeanUtils.copyProperties(dictDataDTO, dictData);
        return dictDataMapper.updateById(dictData) > 0;
    }

    @Override
    public boolean delDictDataById(Long id) {
        return dictDataMapper.deleteById(id) > 0;
    }

    @Override
    public List<Long> delDictDataByIdBatch(List<Long> ids) {
        List<Long> notDelIds = new ArrayList<>();
        for (Long id : ids) {
            if (!delDictDataById(id)) {
                notDelIds.add(id);
            }
        }
        return notDelIds;
    }

    @Override
    public DictData getDictDataById(Long id) {
        return dictDataMapper.selectById(id);
    }

    @Override
    public boolean delDictDataByType(String dictType) {
        DictData dictData = new DictData();
        dictData.setDisabled(true);
        LambdaQueryWrapper<DictData> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DictData::getDictType, dictType);
        return dictDataMapper.update(dictData, lambdaQueryWrapper)>0;
    }
}
