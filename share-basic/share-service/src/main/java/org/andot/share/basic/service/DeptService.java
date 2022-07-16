package org.andot.share.basic.service;

import org.andot.share.basic.dto.DeptDTO;

import java.util.List;

/**
 * 部门服务接口
 * 用于部门创建、修改、删除、查询、分配角色
 *
 * @author lucas
 * @date 2022-07-16 23:08:20
 * @since 1.0
 */
public interface DeptService {

    DeptDTO getDeptById(Long deptId);

    List<DeptDTO> getDeptList(String deptName, String parentCode);

    boolean saveDept(DeptDTO dept);

    boolean updateDept(DeptDTO dept);

    boolean delDeptById(Long id);

    List<DeptDTO> getDeptTree(String deptName, String parentCode);
}
