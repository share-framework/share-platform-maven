package org.andot.share.basic.service;

import org.andot.share.basic.dto.ActionDTO;
import org.andot.share.basic.dto.PageDTO;
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

    /**
     * 根据id获取action详情
     * @param id
     * @return
     */
    ActionDTO getActionById(Long id);

    /**
     * 根据输入模糊查询，获取action列表
     * @param actionDTO
     * @return
     */
    List<ActionDTO> getActionList(ActionDTO actionDTO);

    /**
     * 获取请求地址列表-分页
     * @param actionPage
     * @return
     */
    List<ActionDTO> getActionListOfPage(PageDTO<ActionDTO> actionPage);

    /**
     * 保存接口地址
     * @param actionDTO 接口地址对象
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean saveAction(ActionDTO actionDTO);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean updateAction(ActionDTO actionDTO);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    boolean delActionById(Long id);

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    List<Long> delActionById(List<Long> ids);
}
