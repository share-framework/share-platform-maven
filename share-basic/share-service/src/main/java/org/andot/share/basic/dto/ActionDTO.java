package org.andot.share.basic.dto;

import lombok.Getter;
import lombok.Setter;
import org.andot.share.basic.entity.Action;
import org.andot.share.common.utils.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求地址转换对象
 * @author andot
 */
@Setter
@Getter
public class ActionDTO {
    private Long actionId;
    private String actionName;
    private Byte actionType;
    private String actionUrl;
    private Long menuId;
    private String caption;

    /**
     * 视图实体转数据实体
     * @param actionDTO 对象
     * @return 数据实体
     */
    public static Action action(ActionDTO actionDTO) {
        Action action = new Action();
        if(ObjectUtil.isNotEmpty(actionDTO.actionId)) {
            action.setActionId(actionDTO.actionId);
        }
        action.setActionName(actionDTO.actionName);
        action.setActionType(actionDTO.actionType);
        action.setActionUrl(actionDTO.actionUrl);
        action.setCaption(actionDTO.caption);
        action.setMenuId(actionDTO.getMenuId());
        return action;
    }

    public static ActionDTO action(Action action) {
        ActionDTO actionDTO = new ActionDTO();
        actionDTO.setActionId(action.getActionId());
        actionDTO.setActionName(action.getActionName());
        actionDTO.setActionType(action.getActionType());
        actionDTO.setActionUrl(action.getActionUrl());
        actionDTO.setCaption(action.getCaption());
        actionDTO.setMenuId(action.getMenuId());
        return actionDTO;
    }

    public static List<ActionDTO> action2DTOList(List<Action> actions){
        List<ActionDTO> actionDTOList = new ArrayList<>(actions.size());
        for(Action action : actions) {
            actionDTOList.add(action(action));
        }
        return actionDTOList;
    }

    public static List<Action> dto2ActionList(List<ActionDTO> actionDTOList){
        List<Action> actionList = new ArrayList<>(actionDTOList.size());
        for(ActionDTO actionDTO : actionDTOList) {
            actionList.add(action(actionDTO));
        }
        return actionList;
    }

}
