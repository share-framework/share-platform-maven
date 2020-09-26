package org.andot.share.basic.service;

import org.andot.share.basic.entity.Organ;
import org.andot.share.basic.dto.OrganDTO;

import java.util.List;

/**
 * 组织服务接口
 * 用于组织创建、修改、删除、查询、分配角色
 *
 * @author lucas
 * @date 2020-4-18
 * @since 1.0
 */
public interface OrganService {

    OrganDTO getOrganById(Long roleId);

    List<OrganDTO> getOrganList(String organName);

    boolean saveOrgan(OrganDTO role);

    boolean updateOrgan(OrganDTO role);

    boolean delOrganById(Long id);

    List<OrganDTO> getOrganTree(Long organId);
}
