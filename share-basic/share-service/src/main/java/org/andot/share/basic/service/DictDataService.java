package org.andot.share.basic.service;

import org.andot.share.basic.dto.DictDataDTO;
import org.andot.share.basic.entity.DictData;

import java.util.List;

/**
 * 字典数据服务
 * @author andot
 */
public interface DictDataService {

    /**
     * 根据查询条件获取字典数据列表
     * @param dictData
     * @return
     */
    List<DictData> getDictDataList(DictData dictData);

    /**
     * 根据字典类型获取字段数据
     * @param dictType
     * @return
     */
    List<DictData> getDictDataByType(String dictType);

    /**
     * 保存字段数据
     * @param actionDTO
     * @return
     */
    boolean saveDictData(DictDataDTO actionDTO);

    /**
     * 更新字段数据
     * @param actionDTO
     * @return
     */
    boolean updateDictData(DictDataDTO actionDTO);

    /**
     * 根据id删除字典数据
     * @param id
     * @return
     */
    boolean delDictDataById(Long id);

    /**
     * 根据id批量删除数据
     * @param ids
     * @return
     */
    List<Long> delDictDataByIdBatch(List<Long> ids);

    /**
     * 根据id获取字典数据
     * @param id
     * @return
     */
    DictData getDictDataById(Long id);

    /**
     * 根据类型删除所有
     * @param dictType
     * @return
     */
    boolean delDictDataByType(String dictType);
}
