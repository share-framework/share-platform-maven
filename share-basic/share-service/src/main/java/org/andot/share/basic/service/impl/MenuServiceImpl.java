package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.MenuMapper;
import org.andot.share.basic.dto.MenuTreeDto;
import org.andot.share.basic.entity.AnMenu;
import org.andot.share.basic.service.MenuService;
import org.andot.share.basic.dto.MenuDto;
import org.andot.share.common.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;


    @Override
    public List<MenuDto> getMenuList(String menuName, String url) {
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
        List<MenuDto> menuDtoList = anMenuList.stream().map(anMenu -> {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(anMenu, menuDto);
            return menuDto;
        }).collect(Collectors.toList());
        return menuDtoList;
    }

    @Transactional
    @Override
    public boolean saveMenu(MenuDto menuDto) {
        AnMenu anMenu = new AnMenu();
        BeanUtils.copyProperties(menuDto, anMenu);

        return menuMapper.insert(anMenu) > 0;
    }

    @Transactional
    @Override
    public boolean updateMenu(Long id, MenuDto menuDto) {
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
    public MenuDto getMenuInfoById(Long menuId) {
        AnMenu menu = menuMapper.selectById(menuId);
        MenuDto menuDto = new MenuDto();
        BeanUtils.copyProperties(menu, menuDto);
        return menuDto;
    }

    @Override
    public List<MenuTreeDto> getMenuTreeList(Long xNumber) {
        List<AnMenu> menuList = menuMapper.getMenuListByUserId(xNumber);
        Map<String, List<AnMenu>> menuListMap = menuList.stream()
                .filter(menu -> ObjectUtil.isNotEmpty(menu))
                .collect(Collectors.groupingBy(AnMenu::getParentCode));
        return gen(menuListMap, "0");
    }

    private List<MenuTreeDto> gen(Map<String, List<AnMenu>> menuListMap, String parentCode){
        List<MenuTreeDto> list = new LinkedList<>();
        List<AnMenu> menuList = menuListMap.get(parentCode);
        if(menuList != null) {
            for (int i = 0; i < menuList.size(); i++) {
                MenuTreeDto menuTreeDto = new MenuTreeDto();
                if (menuListMap.get(menuList.get(i).getMenuCode()) != null && menuListMap.get(menuList.get(i).getMenuCode()).size() > 0) {
                    menuTreeDto.setChildren(gen(menuListMap, menuList.get(i).getMenuCode()));
                }
                menuTreeDto.setName(menuList.get(i).getMenuName());
                menuTreeDto.setAppSystemId(menuList.get(i).getAppSystemId());
                menuTreeDto.setIcon(menuList.get(i).getMenuIcon());
                menuTreeDto.setMenuParentCode(menuList.get(i).getParentCode());
                menuTreeDto.setType(menuList.get(i).getMenuType());
                menuTreeDto.setUrl(menuList.get(i).getMenuUrl());
                menuTreeDto.setSort(menuList.get(i).getOrderCode());
                menuTreeDto.setId(menuList.get(i).getMenuCode());
                menuTreeDto.setRedirect(menuList.get(i).getRedirect());
                menuTreeDto.setComponent(menuList.get(i).getComponent());
                list.add(menuTreeDto);
            }
        }
        return list;
    }
}
