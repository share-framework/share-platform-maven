package org.andot.share.basic.service;

import org.andot.share.basic.dto.MenuDto;
import org.andot.share.basic.dto.MenuTreeDto;

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

    boolean saveMenu(MenuDto menuDto);

    boolean updateMenu(Long id, MenuDto menuDto);

    boolean delMenuById(Long id);

    boolean delBatchMenuById(List<Long> id);

    List<MenuTreeDto> getMenuTreeList(Long xnumber);
}
