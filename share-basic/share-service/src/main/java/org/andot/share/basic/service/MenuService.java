package org.andot.share.basic.service;

import org.andot.share.basic.dto.MenuDto;
import org.andot.share.basic.dto.MenuTreeDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单服务接口
 * 用于菜单创建、修改、删除、查询、分配角色
 *
 * @author lucas
 * @date 2020-4-18
 * @since 1.0
 */
public interface MenuService {

    MenuDto getMenuInfoById(Long menuId);

    List<MenuDto> getMenuList(String menuName, String url);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveMenu(MenuDto menuDto);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateMenu(Long id, MenuDto menuDto);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delMenuById(Long id);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delBatchMenuById(List<Long> id);

    List<MenuTreeDto> getMenuTreeList(Long appSystemId, Long xumber);

    List<MenuTreeDto> getManageMenuTreeList(Long appSystemId);
}
