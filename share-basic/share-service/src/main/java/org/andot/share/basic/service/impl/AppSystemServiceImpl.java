package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.AppSystemMapper;
import org.andot.share.basic.dto.AppSystemDTO;
import org.andot.share.basic.entity.AppSystem;
import org.andot.share.basic.service.AppSystemService;
import org.andot.share.common.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("appSystemService")
public class AppSystemServiceImpl implements AppSystemService {

    private final AppSystemMapper appSystemMapper;

    @Override
    public AppSystemDTO getAppSystemById(Long id) {
        AppSystem appSystem = appSystemMapper.selectById(id);
        AppSystemDTO appSystemDTO = new AppSystemDTO();
        BeanUtils.copyProperties(appSystem, appSystemDTO);
        return appSystemDTO;
    }

    @Override
    public List<AppSystemDTO> getAppSystemList(String appSystemName, String appSystemUrl) {
        LambdaQueryWrapper<AppSystem> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtil.isNotEmpty(appSystemName)) {
            queryWrapper.like(AppSystem::getAppSystemName, appSystemName);
        }
        if(ObjectUtil.isNotEmpty(appSystemUrl)) {
            queryWrapper.like(AppSystem::getAppSystemUrl, appSystemUrl);
        }
        List<AppSystem> systemList = appSystemMapper.selectList(queryWrapper);
        List<AppSystemDTO> appSystemDTOList = systemList.stream().map(appSystem -> {
            AppSystemDTO appSystemDTO = new AppSystemDTO();
            BeanUtils.copyProperties(appSystem, appSystemDTO);
            return appSystemDTO;
        }).collect(Collectors.toList());
        return appSystemDTOList;
    }

    @Override
    public boolean saveAppSystem(AppSystemDTO appSystemDTO) {
        AppSystem appSystem = new AppSystem();
        BeanUtils.copyProperties(appSystemDTO, appSystem);
        return appSystemMapper.insert(appSystem)>0;
    }

    @Override
    public boolean updateAppSystem(AppSystemDTO appSystemDTO) {
        AppSystem appSystem = new AppSystem();
        BeanUtils.copyProperties(appSystemDTO, appSystem);
        return appSystemMapper.insert(appSystem)>0;
    }

    @Override
    public boolean delAppSystemById(Long id) {
        return appSystemMapper.deleteById(id)>0;
    }
}
