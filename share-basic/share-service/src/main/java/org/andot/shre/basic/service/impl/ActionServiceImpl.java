package org.andot.shre.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.andot.share.basic.dao.ActionMapper;
import org.andot.share.basic.entity.Action;
import org.andot.shre.basic.service.ActionService;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("actionService")
public class ActionServiceImpl implements ActionService {

    private final ActionMapper actionMapper;

    @Override
    public Action getActionById(Long roleId) {
        return actionMapper.selectById(roleId);
    }

    @Override
    public List<Action> getActionList(String actionName, String actionUrl) {
        return actionMapper.selectList(new LambdaQueryWrapper<Action>().like(Action::getActionName, actionName).or().like(Action::getActionUrl, actionName));
    }

    @Override
    public boolean saveAction(Action role) {
        return actionMapper.insert(role) > 0;
    }

    @Override
    public boolean updateAction(Action role) {
        return actionMapper.updateById(role) > 0;
    }

    @Override
    public boolean delActionById(Long id) {
        return actionMapper.deleteById(id) > 0;
    }
}
