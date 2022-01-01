package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.andot.share.basic.dao.MenuElementMapper;
import org.andot.share.basic.entity.MenuElement;
import org.andot.share.basic.service.MenuElementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单元素关系表实现
 * @author andot
 * @since 1.0.0
 */
@Service("menuElementService")
@RequiredArgsConstructor
public class MenuElementServiceImpl implements MenuElementService {

    private final MenuElementMapper menuElementMapper;


    @Override
    public List<MenuElement> getMenuElementList(Long menuId) {
        List<MenuElement> menuElements = menuElementMapper.selectList(
                new LambdaQueryWrapper<MenuElement>().eq(MenuElement::getMenuId, menuId));
        return menuElements;
    }

    @Override
    public boolean saveMenuElement(MenuElement menuElement) {
        return menuElementMapper.insert(menuElement) > 0;
    }

    @Override
    public boolean updateMenuElement(Long id, MenuElement menuElement) {
        menuElement.setMenuElementId(id);
        return menuElementMapper.updateById(menuElement) > 0;
    }

    @Override
    public boolean delMenuElementById(Long id) {
        return menuElementMapper.deleteById(id) > 0;
    }

    @Override
    public boolean delBatchMenuElementById(List<Long> ids) {
        return menuElementMapper.deleteBatchIds(ids)>0;
    }
}
