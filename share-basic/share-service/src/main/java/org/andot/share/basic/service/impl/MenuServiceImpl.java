package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.MenuMapper;
import org.andot.share.basic.dto.MenuPermissionDTO;
import org.andot.share.basic.dto.MenuTreeDTO;
import org.andot.share.basic.dto.MenuTreeSelectorDTO;
import org.andot.share.basic.entity.AnMenu;
import org.andot.share.basic.service.MenuService;
import org.andot.share.basic.dto.MenuDTO;
import org.andot.share.common.utils.JSONObject;
import org.andot.share.common.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;


    @Override
    public List<MenuDTO> getMenuList(String menuName, String url) {
        LambdaQueryWrapper<AnMenu> lambdaQueryWrapper = new LambdaQueryWrapper<AnMenu>();
        if (menuName != null && url != null) {
            lambdaQueryWrapper.like(AnMenu::getMenuName, menuName).or().like(AnMenu::getMenuUrl, url);
        } else {
            if (menuName != null) {
                lambdaQueryWrapper.like(AnMenu::getMenuName, menuName);
            } else if (url != null) {
                lambdaQueryWrapper.like(AnMenu::getMenuUrl, url);
            }
        }

        List<AnMenu> anMenuList = menuMapper.selectList(lambdaQueryWrapper);
        List<MenuDTO> menuDTOList = anMenuList.stream().map(anMenu -> {
            MenuDTO menuDto = new MenuDTO();
            BeanUtils.copyProperties(anMenu, menuDto);
            return menuDto;
        }).collect(Collectors.toList());
        return menuDTOList;
    }

    @Transactional
    @Override
    public boolean saveMenu(MenuDTO menuDto) {
        AnMenu anMenu = new AnMenu();
        BeanUtils.copyProperties(menuDto, anMenu);

        return menuMapper.insert(anMenu) > 0;
    }

    @Transactional
    @Override
    public boolean updateMenu(Long id, MenuDTO menuDto) {
        AnMenu anMenu = new AnMenu();
        BeanUtils.copyProperties(menuDto, anMenu);
        anMenu.setMenuId(id);
        return menuMapper.updateById(anMenu) > 0;
    }

    @Transactional
    @Override
    public boolean delMenuById(Long id) {
        return menuMapper.deleteById(id) > 0;
    }

    @Transactional
    @Override
    public boolean delBatchMenuById(List<Long> id) {
        for (int i = 0; i < id.size(); i++) {
            this.delMenuById(id.get(i));
        }
        return true;
    }

    @Override
    public MenuDTO getMenuInfoById(Long menuId) {
        AnMenu menu = menuMapper.selectById(menuId);
        MenuDTO menuDto = new MenuDTO();
        BeanUtils.copyProperties(menu, menuDto);
        return menuDto;
    }

    @Override
    public List<MenuTreeDTO> getMenuTreeList(Long appSystemId, Long xNumber) {
        List<AnMenu> menuList = menuMapper.getMenuListByUserId(appSystemId, xNumber);
        Map<String, List<AnMenu>> menuListMap = menuList.stream()
                .filter(menu -> ObjectUtil.isNotEmpty(menu))
                .collect(Collectors.groupingBy(AnMenu::getMenuParentCode));
        return gen(menuListMap, "0");
    }

    @Override
    public List<MenuTreeDTO> getManageMenuTreeList(Long appSystemId) {
        List<AnMenu> menuList = menuMapper.selectList(new LambdaQueryWrapper<AnMenu>()
                .eq(AnMenu::getAppSystemId, appSystemId));
        Map<String, List<AnMenu>> menuListMap = menuList.stream()
                .filter(menu -> ObjectUtil.isNotEmpty(menu))
                .collect(Collectors.groupingBy(AnMenu::getMenuParentCode));
        return gen(menuListMap, "0");
    }

    @Override
    public HashMap getManageMenuList(Long appSystemId, Long roleId) {
        // 分两个接口查询
        List<MenuPermissionDTO> menuAllList = menuMapper.getMenuListByRoleId(appSystemId, null);
        List<MenuPermissionDTO> menuList = menuMapper.getMenuListByRoleId(appSystemId, roleId);
        List<Long> menuIds = menuList.stream()
                .filter(menuPermissionDTO -> !menuPermissionDTO.getMenuParentCode().equals("0"))
                .filter(menuPermissionDTO -> menuPermissionDTO.getRoleMenuId() != 0)
                .map(item -> item.getMenuId()).collect(Collectors.toList());
        Map<String, List<MenuPermissionDTO>> menuListMap = menuAllList.stream()
                .filter(menu -> ObjectUtil.isNotEmpty(menu))
                .collect(Collectors.groupingBy(MenuPermissionDTO::getMenuParentCode));
        List<MenuTreeSelectorDTO> menuListSelector = genSelector(menuListMap, "0");
        JSONObject result = new JSONObject();
        result.put("menuList", menuListSelector);
        result.put("menuIds", menuIds);
        return result;
    }

    /**
     * 递归获取到所有菜单
     * @param menuListMap
     * @param parentCode
     * @return
     */
    private List<MenuTreeDTO> gen(Map<String, List<AnMenu>> menuListMap, String parentCode){
        List<MenuTreeDTO> list = new LinkedList<>();
        List<AnMenu> menuList = menuListMap.get(parentCode);
        if(menuList != null) {
            for (int i = 0; i < menuList.size(); i++) {
                MenuTreeDTO menuTreeDto = new MenuTreeDTO();
                if (menuListMap.get(menuList.get(i).getMenuCode()) != null && menuListMap.get(menuList.get(i).getMenuCode()).size() > 0) {
                    menuTreeDto.setChildren(gen(menuListMap, menuList.get(i).getMenuCode()));
                    menuTreeDto.setHashChildren(menuTreeDto.getChildren().size()>0);
                }
                menuTreeDto.setName(menuList.get(i).getMenuName());
                menuTreeDto.setAppSystemId(menuList.get(i).getAppSystemId());
                menuTreeDto.setIcon(menuList.get(i).getMenuIcon());
                menuTreeDto.setMenuParentCode(menuList.get(i).getMenuParentCode());
                menuTreeDto.setType(menuList.get(i).getMenuType());
                menuTreeDto.setUrl(menuList.get(i).getMenuUrl());
                menuTreeDto.setSort(menuList.get(i).getOrderCode());
                menuTreeDto.setId(menuList.get(i).getMenuId());
                menuTreeDto.setMenuCode(menuList.get(i).getMenuCode());
                menuTreeDto.setRedirect(menuList.get(i).getRedirect());
                menuTreeDto.setComponent(menuList.get(i).getComponent());
                menuTreeDto.setDisabled(menuList.get(i).getDisabled());
                list.add(menuTreeDto);
            }
        }
        return list;
    }

    private List<MenuTreeSelectorDTO> genSelector(Map<String, List<MenuPermissionDTO>> menuListMap, String parentCode){
        List<MenuTreeSelectorDTO> list = new LinkedList<>();
        List<MenuPermissionDTO> menuList = menuListMap.get(parentCode);
        if(menuList != null) {
            for (int i = 0; i < menuList.size(); i++) {
                MenuTreeSelectorDTO menuTreeDto = new MenuTreeSelectorDTO();
                if (menuListMap.get(menuList.get(i).getMenuCode()) != null && menuListMap.get(menuList.get(i).getMenuCode()).size() > 0) {
                    menuTreeDto.setChildren(genSelector(menuListMap, menuList.get(i).getMenuCode()));
                    menuTreeDto.setHashChildren(menuTreeDto.getChildren().size()>0);
                }
                menuTreeDto.setMenuId(menuList.get(i).getMenuId());
                menuTreeDto.setMenuName(menuList.get(i).getMenuName());
                menuTreeDto.setMenuCode(menuList.get(i).getMenuCode());
                menuTreeDto.setRoleMenuId(menuList.get(i).getRoleMenuId());
                list.add(menuTreeDto);
            }
        }
        return list;
    }
}
