package org.andot.share.basic.service;

import org.andot.share.basic.entity.Icons;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IconsService {
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveIcons(Icons icons);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateIcons(Long id, Icons icons);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delIconById(Long id);

    List<Icons> getIconList(Icons icons);
}
