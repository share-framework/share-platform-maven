package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.IconsMapper;
import org.andot.share.basic.entity.Icons;
import org.andot.share.basic.service.IconsService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("iconsService")
public class IconsServiceImpl implements IconsService {

    private final IconsMapper iconsMapper;

    @Override
    public boolean saveIcons(Icons icons) {
        return iconsMapper.insert(icons)>0;
    }

    @Override
    public boolean updateIcons(Long id, Icons icons) {
        icons.setId(id);
        return iconsMapper.updateById(icons)>0;
    }

    @Override
    public boolean delIconById(Long id) {
        return iconsMapper.deleteById(id)>0;
    }

    @Override
    public List<Icons> getIconList(Icons icons) {
        LambdaQueryWrapper<Icons> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (icons.getIconType() != null) {
            lambdaQueryWrapper.eq(Icons::getIconType, icons.getIconType());
        }
        return iconsMapper.selectList(lambdaQueryWrapper);
    }
}
