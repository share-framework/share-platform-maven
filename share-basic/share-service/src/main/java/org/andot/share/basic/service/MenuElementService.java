package org.andot.share.basic.service;

import org.andot.share.basic.entity.MenuElement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单元素服务
 * @author andot
 */
public interface MenuElementService {
    /**
     * 根据菜单id查询菜单下所有元素
     * @param menuId 菜单id
     * @return 页面元素列表
     */
    List<MenuElement> getMenuElementList(Long menuId);

    /**
     *
     * @param menuElement
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveMenuElement(MenuElement menuElement);

    /**
     *  更新元素
     * @param id
     * @param menuElement
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateMenuElement(Long id, MenuElement menuElement);

    /**
     * 根据元素id删除
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delMenuElementById(Long id);

    /**
     * 更具元素id页面批量删除元素
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delBatchMenuElementById(List<Long> id);
}
