package org.andot.share.basic.service;

import org.andot.share.basic.entity.ResourcesGroups;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源组服务
 * @author andot
 */
public interface ResourcesGroupsService {
    /**
     * 根据菜单id查询菜单下所有元素
     * @param menuId 菜单id
     * @return 页面元素列表
     */
    List<ResourcesGroups> getResourcesGroupsList(Long menuId);

    /**
     *  保存资源组
     * @param ResourcesGroups
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveResourcesGroups(ResourcesGroups ResourcesGroups);

    /**
     *  更新元素
     * @param id
     * @param ResourcesGroups
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateResourcesGroups(Long id, ResourcesGroups ResourcesGroups);

    /**
     * 根据元素id删除
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delResourcesGroupsById(Long id);

    /**
     * 更具元素id页面批量删除元素
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delBatchResourcesGroupsById(List<Long> id);
}
