package org.andot.share.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.lang.Collections;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.ActionMapper;
import org.andot.share.basic.dto.ActionDTO;
import org.andot.share.basic.dto.PageDTO;
import org.andot.share.basic.entity.Action;
import org.andot.share.basic.service.ActionService;
import org.andot.share.common.response.CommonPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @see org.andot.share.basic.service.ActionService
 * @author andot
 */
@AllArgsConstructor
@Service("actionService")
public class ActionServiceImpl implements ActionService {

    private final ActionMapper actionMapper;

    @Override
    public ActionDTO getActionById(Long id) {
        return ActionDTO.action(actionMapper.selectById(id));
    }

    @Override
    public List<ActionDTO> getActionList(ActionDTO actionDTO) {
        LambdaQueryWrapper<Action> queryWrapper = new LambdaQueryWrapper<>();
        if(actionDTO.getMenuId() != null && actionDTO.getMenuId() != 0) {
            queryWrapper.eq(Action::getMenuId, actionDTO.getMenuId());
        }
        queryWrapper.like(Action::getActionName, actionDTO.getActionName()).or()
                .like(Action::getActionUrl, actionDTO.getActionUrl());
        return ActionDTO.action2DTOList(actionMapper.selectList(queryWrapper));
    }

    @Override
    public List<ActionDTO> getActionListOfPage(PageDTO<ActionDTO> actionPage) {
        return ActionDTO.action2DTOList(actionMapper.selectList(new LambdaQueryWrapper<Action>()
                .like(Action::getActionName, actionPage.getParam().getActionName()).or()
                .like(Action::getActionUrl, actionPage.getParam().getActionUrl())));
    }

    @Override
    public boolean saveAction(ActionDTO actionDTO) {
        return actionMapper.insert(ActionDTO.action(actionDTO)) > 0;
    }

    @Override
    public boolean updateAction(ActionDTO actionDTO) {
        return actionMapper.updateById(ActionDTO.action(actionDTO)) > 0;
    }

    @Override
    public boolean delActionById(Long id) {
        return actionMapper.deleteById(id) > 0;
    }

    @Override
    public List<Long> delActionById(List<Long> ids) {
        if(!Collections.isEmpty(ids)) {
            List<Long> idsNo = new ArrayList<>(ids.size() / 2);
            for (Long id : ids) {
                if(!delActionById(id)) {
                    idsNo.add(id);
                }
            }
            return idsNo;
        }
        return java.util.Collections.EMPTY_LIST;
    }
}
