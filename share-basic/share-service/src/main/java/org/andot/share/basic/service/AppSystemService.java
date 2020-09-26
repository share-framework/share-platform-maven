package org.andot.share.basic.service;

import org.andot.share.basic.dto.AppSystemDTO;
import org.andot.share.basic.entity.AppSystem;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppSystemService {
    AppSystemDTO getAppSystemById(Long id);

    List<AppSystemDTO> getAppSystemList(String appSystemName, String appSystemUrl);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveAppSystem(AppSystemDTO appSystem);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateAppSystem(AppSystemDTO appSystem);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delAppSystemById(Long id);
}
