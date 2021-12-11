package org.andot.share.basic.service;

import org.andot.share.basic.entity.PageElement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 页面元素服务
 * @author andot
 */
public interface PageElementService {
    /**
     * 根据菜单id查询菜单下所有元素
     * @param menuId 菜单id
     * @return 页面元素列表
     */
    List<PageElement> getPageElementList(Long menuId);

    /**
     *
     * @param PageElement
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean savePageElement(PageElement PageElement);

    /**
     *  更新元素
     * @param id
     * @param PageElement
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updatePageElement(Long id, PageElement PageElement);

    /**
     * 根据元素id删除
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delPageElementById(Long id);

    /**
     * 更具元素id页面批量删除元素
     * @param id
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delBatchPageElementById(List<Long> id);
}
