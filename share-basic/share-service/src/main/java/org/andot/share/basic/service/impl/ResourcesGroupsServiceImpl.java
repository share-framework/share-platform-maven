package org.andot.share.basic.service.impl;

import lombok.RequiredArgsConstructor;
import org.andot.share.basic.entity.ResourcesGroups;
import org.andot.share.basic.service.ResourcesGroupsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author andot
 */
@Service("resourcesGroupsService")
@RequiredArgsConstructor
public class ResourcesGroupsServiceImpl implements ResourcesGroupsService {



    @Override
    public List<ResourcesGroups> getResourcesGroupsList(Long groupId) {

        return null;
    }

    @Override
    public boolean saveResourcesGroups(ResourcesGroups ResourcesGroups) {
        return false;
    }

    @Override
    public boolean updateResourcesGroups(Long id, ResourcesGroups ResourcesGroups) {
        return false;
    }

    @Override
    public boolean delResourcesGroupsById(Long id) {
        return false;
    }

    @Override
    public boolean delBatchResourcesGroupsById(List<Long> id) {
        return false;
    }
}
