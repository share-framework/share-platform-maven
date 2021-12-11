package org.andot.share.basic.service.impl;

import lombok.RequiredArgsConstructor;
import org.andot.share.basic.entity.MenuElement;
import org.andot.share.basic.service.MenuElementService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单元素服务实现
 * @author andot
 * @since 1.0.0
 */
@Service("menuElementService")
@RequiredArgsConstructor
public class MenuElementServiceImpl implements MenuElementService {
    @Override
    public List<MenuElement> getMenuElementList(Long menuId) {
        return null;
    }

    @Override
    public boolean saveMenuElement(MenuElement menuElement) {
        return false;
    }

    @Override
    public boolean updateMenuElement(Long id, MenuElement menuElement) {
        return false;
    }

    @Override
    public boolean delMenuElementById(Long id) {
        return false;
    }

    @Override
    public boolean delBatchMenuElementById(List<Long> id) {
        return false;
    }
}
