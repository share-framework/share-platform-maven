package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.OrganMapper;
import org.andot.share.basic.entity.Organ;
import org.andot.share.basic.dto.OrganDTO;
import org.andot.share.basic.service.OrganService;
import org.andot.share.common.exception.HaveSubItemException;
import org.andot.share.common.type.DeleteType;
import org.andot.share.common.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
    public List<OrganDTO> getOrganList(String organName, String parentCode) {
        LambdaQueryWrapper<Organ> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtil.isNotEmpty(organName)) {
            queryWrapper.like(Organ::getOrganName, organName);
        }
        queryWrapper.eq(Organ::getIsDel, DeleteType.NORMAL.getValue());
        List<Organ> organList = organMapper.selectList(queryWrapper);
        queryWrapper.eq(Organ::getOrganParentCode, parentCode);
        List<OrganDTO> organDTOList = organList.stream().map(organ -> {
            OrganDTO organDTO = new OrganDTO();
            BeanUtils.copyProperties(organ, organDTO);
            organDTO.setHasChildren(true);
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
        Organ organSource = organMapper.selectById(id);
        List<Organ> organs = organMapper.selectList(new LambdaQueryWrapper<Organ>()
                .eq(Organ::getOrganParentCode, organSource.getOrganCode())
                .eq(Organ::getIsDel, DeleteType.NORMAL.getValue()));
        if (CollectionUtils.isEmpty(organs)) {
            Organ organ = new Organ();
            organ.setOrganId(id);
            organ.setIsDel(DeleteType.DELETE.getValue());
            return organMapper.updateById(organ)>0;
        } else {
            throw new HaveSubItemException("该组织下还有数据，请先删除子组织数据，再删除该组织。");
        }
    }

    @Override
    public List<OrganDTO> getOrganTree(String organName, String parentCode) {
        LambdaQueryWrapper<Organ> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtil.isNotEmpty(organName)) {
            queryWrapper.like(Organ::getOrganName, organName);
        }
        queryWrapper.eq(Organ::getIsDel, DeleteType.NORMAL.getValue());
        List<Organ> organList = organMapper.selectList(queryWrapper);
        Map<String, List<Organ>> map = organList.stream().collect(Collectors.groupingBy(Organ::getOrganParentCode));
        List<OrganDTO> organDTOList = gen(map, parentCode);
        return organDTOList;
    }


    /**
     * 递归获取到所有菜单
     * @param listMap
     * @param parentCode
     * @return
     */
    private List<OrganDTO> gen(Map<String, List<Organ>> listMap, String parentCode){
        List<OrganDTO> list = new LinkedList<>();
        List<Organ> organList = listMap.get(parentCode);
        if(organList != null) {
            for (int i = 0; i < organList.size(); i++) {
                OrganDTO organDTO = new OrganDTO();
                if (listMap.get(organList.get(i).getOrganCode()) != null && listMap.get(organList.get(i).getOrganCode()).size() > 0) {
                    organDTO.setChildren(gen(listMap, organList.get(i).getOrganCode()));
                    organDTO.setHasChildren(organDTO.getChildren().size()>0);
                }
                organDTO.setOrganName(organList.get(i).getOrganName());
                organDTO.setOrganParentCode(organList.get(i).getOrganParentCode());
                organDTO.setOrganType(organList.get(i).getOrganType());
                organDTO.setOrganUrl(organList.get(i).getOrganUrl());
                organDTO.setOrderCode(organList.get(i).getOrderCode());
                organDTO.setOrganId(organList.get(i).getOrganId());
                organDTO.setOrganCode(organList.get(i).getOrganCode());
                list.add(organDTO);
            }
        }
        return list;
    }
}
