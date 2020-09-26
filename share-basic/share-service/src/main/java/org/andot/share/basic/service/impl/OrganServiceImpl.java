package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.OrganMapper;
import org.andot.share.basic.entity.Organ;
import org.andot.share.basic.dto.OrganDTO;
import org.andot.share.basic.service.OrganService;
import org.andot.share.common.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service("organService")
public class OrganServiceImpl implements OrganService {

    private final OrganMapper organMapper;

    @Override
    public OrganDTO getOrganById(Long id) {
        Organ organ = organMapper.selectById(id);
        OrganDTO organDTO = new OrganDTO();
        BeanUtils.copyProperties(organ, organDTO);
        return organDTO;
    }

    @Override
    public List<OrganDTO> getOrganList(String organName) {
        LambdaQueryWrapper<Organ> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtil.isNotEmpty(organName)) {
            queryWrapper.like(Organ::getOrganName, organName);
        }
        List<Organ> organList = organMapper.selectList(queryWrapper);
        List<OrganDTO> organDTOList = organList.stream().map(organ -> {
            OrganDTO organDTO = new OrganDTO();
            BeanUtils.copyProperties(organ, organDTO);
            return organDTO;
        }).collect(Collectors.toList());
        return organDTOList;
    }

    @Override
    public boolean saveOrgan(OrganDTO organDTO) {
        Organ organ = new Organ();
        BeanUtils.copyProperties(organDTO, organ);
        return organMapper.insert(organ)>0;
    }

    @Override
    public boolean updateOrgan(OrganDTO organDTO) {
        Organ organ = new Organ();
        BeanUtils.copyProperties(organDTO, organ);
        return organMapper.updateById(organ)>0;
    }

    @Override
    public boolean delOrganById(Long id) {
        return organMapper.deleteById(id)>0;
    }

    @Override
    public List<OrganDTO> getOrganTree(Long organId) {
        return null;
    }
}
