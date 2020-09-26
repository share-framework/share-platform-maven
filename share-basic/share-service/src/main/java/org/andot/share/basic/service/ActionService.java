package org.andot.share.basic.service;

import org.andot.share.basic.entity.Action;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 请求地址服务接口
 * 用于请求地址创建、修改、删除、查询、分配角色
 *
 * @author lucas
 * @date 2020-4-18
 * @since 1.0
 */
public interface ActionService {

    Action getActionById(Long roleId);

    List<Action> getActionList(String actionName, String actionUrl);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveAction(Action role);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateAction(Action role);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delActionById(Long id);
}
