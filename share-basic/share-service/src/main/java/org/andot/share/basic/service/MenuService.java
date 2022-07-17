package org.andot.share.basic.service;

import org.andot.share.basic.dto.MenuDTO;
import org.andot.share.basic.dto.MenuTreeDTO;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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

    MenuDTO getMenuInfoById(Long menuId);

    List<MenuDTO> getMenuList(String menuName, String url);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveMenu(MenuDTO menuDto);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateMenu(Long id, MenuDTO menuDto);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delMenuById(Long id);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delBatchMenuById(List<Long> id);

    List<MenuTreeDTO> getMenuTreeList(Long appSystemId, Long xumber);

    List<MenuTreeDTO> getManageMenuTreeList(Long appSystemId);

    /**
     * 查询当前系统的菜单和角色关系列表
     * @param appSystemId 应用系统id
     * @param roleCode 角色code
     * @return
     */
    HashMap getManageMenuList(Long appSystemId, String roleCode);


    /**
     *
     * @param appSystemId
     * @param roles
     * @return
     */
    List<String> getMenuCodeListByRoleCodes (Long appSystemId, List<String> roles);
}
