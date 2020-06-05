package org.andot.shre.basic.service.impl;

import org.andot.share.basic.entity.Organ;
import org.andot.shre.basic.dto.OrganDto;
import org.andot.shre.basic.service.OrganService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("organService")
public class OrganServiceImpl implements OrganService {
    @Override
    public Organ getOrganById(Long roleId) {
        return null;
    }

    @Override
    public List<Organ> getOrganList(String organName) {
        return null;
    }

    @Override
    public boolean saveOrgan(Organ role) {
        return false;
    }

    @Override
    public boolean updateOrgan(Organ role) {
        return false;
    }

    @Override
    public boolean delOrganById(Long id) {
        return false;
    }

    @Override
    public List<OrganDto> getOrganTree(Long organId) {
        return null;
    }
}
