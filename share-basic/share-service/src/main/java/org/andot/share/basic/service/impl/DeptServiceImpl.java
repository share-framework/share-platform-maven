package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.DeptMapper;
import org.andot.share.basic.dto.DeptDTO;
import org.andot.share.basic.entity.Dept;
import org.andot.share.basic.service.DeptService;
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
@Service("deptService")
public class DeptServiceImpl implements DeptService {

    private final DeptMapper deptMapper;

    @Override
    public DeptDTO getDeptById(Long id) {
        Dept dept = deptMapper.selectById(id);
        DeptDTO deptDTO = new DeptDTO();
        BeanUtils.copyProperties(dept, deptDTO);
        return deptDTO;
    }

    @Override
    public List<DeptDTO> getDeptList(String deptName, String parentCode) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtil.isNotEmpty(deptName)) {
            queryWrapper.like(Dept::getDeptName, deptName);
        }
        queryWrapper.eq(Dept::getIsDel, DeleteType.NORMAL.getValue());
        List<Dept> deptList = deptMapper.selectList(queryWrapper);
        queryWrapper.eq(Dept::getDeptParentCode, parentCode);
        List<DeptDTO> deptDTOList = deptList.stream().map(dept -> {
            DeptDTO deptDTO = new DeptDTO();
            BeanUtils.copyProperties(dept, deptDTO);
            deptDTO.setHasChildren(true);
            return deptDTO;
        }).collect(Collectors.toList());
        return deptDTOList;
    }

    @Override
    public boolean saveDept(DeptDTO deptDTO) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptDTO, dept);
        return deptMapper.insert(dept)>0;
    }

    @Override
    public boolean updateDept(DeptDTO deptDTO) {
        Dept dept = new Dept();
        BeanUtils.copyProperties(deptDTO, dept);
        return deptMapper.updateById(dept)>0;
    }

    @Override
    public boolean delDeptById(Long id) {
        Dept deptSource = deptMapper.selectById(id);
        List<Dept> depts = deptMapper.selectList(new LambdaQueryWrapper<Dept>()
                .eq(Dept::getDeptParentCode, deptSource.getDeptCode())
                .eq(Dept::getIsDel, DeleteType.NORMAL.getValue()));
        if (CollectionUtils.isEmpty(depts)) {
            Dept dept = new Dept();
            dept.setDeptId(id);
            dept.setIsDel(DeleteType.DELETE.getValue());
            return deptMapper.updateById(dept)>0;
        } else {
            throw new HaveSubItemException("该组织下还有数据，请先删除子组织数据，再删除该组织。");
        }
    }

    @Override
    public List<DeptDTO> getDeptTree(String deptName, String parentCode) {
        LambdaQueryWrapper<Dept> queryWrapper = new LambdaQueryWrapper<>();
        if(ObjectUtil.isNotEmpty(deptName)) {
            queryWrapper.like(Dept::getDeptName, deptName);
        }
        queryWrapper.eq(Dept::getIsDel, DeleteType.NORMAL.getValue());
        List<Dept> deptList = deptMapper.selectList(queryWrapper);
        Map<String, List<Dept>> map = deptList.stream().collect(Collectors.groupingBy(Dept::getDeptParentCode));
        List<DeptDTO> deptDTOList = gen(map, parentCode);
        return deptDTOList;
    }


    /**
     * 递归获取到所有菜单
     * @param listMap
     * @param parentCode
     * @return
     */
    private List<DeptDTO> gen(Map<String, List<Dept>> listMap, String parentCode){
        List<DeptDTO> list = new LinkedList<>();
        List<Dept> deptList = listMap.get(parentCode);
        if(deptList != null) {
            for (int i = 0; i < deptList.size(); i++) {
                DeptDTO deptDTO = new DeptDTO();
                if (listMap.get(deptList.get(i).getDeptCode()) != null && listMap.get(deptList.get(i).getDeptCode()).size() > 0) {
                    deptDTO.setChildren(gen(listMap, deptList.get(i).getDeptCode()));
                    deptDTO.setHasChildren(deptDTO.getChildren().size()>0);
                }
                deptDTO.setDeptName(deptList.get(i).getDeptName());
                deptDTO.setDeptParentCode(deptList.get(i).getDeptParentCode());
                deptDTO.setDeptType(deptList.get(i).getDeptType());
                deptDTO.setSort(deptList.get(i).getSort());
                deptDTO.setDeptId(deptList.get(i).getDeptId());
                deptDTO.setDeptCode(deptList.get(i).getDeptCode());
                list.add(deptDTO);
            }
        }
        return list;
    }
}
