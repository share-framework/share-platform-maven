package org.andot.share.basic.service;

import org.andot.share.basic.dto.DictTypeDTO;
import org.andot.share.basic.entity.DictType;

import java.util.List;

/**
 *
 * @author andot
 */
public interface DictTypeService {
    /**
     * 加载字典缓存数据
     */
    void loadingDictCache();

    /**
     * 清空字典缓存数据
     */
    void clearDictCache();

    /**
     * 重置字典缓存数据
     */
    void resetDictCache();

    /**
     * 根据查询 条件查询列表
     * @param dictType
     * @return
     */
    List<DictType> getDictTypeList(DictType dictType);

    /**
     * 检测字典类型唯一的数据
     * @param dictTypeDTO
     * @return
     */
    Boolean checkDictTypeUnique(DictTypeDTO dictTypeDTO);

    /**
     * 根据id获取详细信息
     * @param dictId
     * @return
     */
    DictType getDictTypeById(Long dictId);

    /**
     * 保存字典类型信息
     * @param dictTypeDTO
     * @return
     */
    boolean saveDictType(DictTypeDTO dictTypeDTO);

    /**
     * 更新字典类型信息
     * @param dictTypeDTO
     * @return
     */
    boolean updateDictType(DictTypeDTO dictTypeDTO);

    /**
     * 删除字典类型
     * @param dictTypeId
     */
    boolean delDictTypeById(Long dictTypeId);

    /**
     * 批量删除字典类型
     * @param dictTypeIds
     */
    List<Long> delDictTypeByIds(List<Long> dictTypeIds);

    /**
     * 获取有效的全量的字典数据
     * @return
     */
    List<DictType> getDictTypeAll();
}
