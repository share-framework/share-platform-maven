package org.andot.share.core.util;

import org.andot.share.common.type.ConstantType;
import org.andot.share.core.dto.RoleDTO;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class GodUtil {
    public static String god(String xNumber) {
        if (ConstantType.GOD_USER_NUMBER.toString().equals(xNumber)) {
            return null;
        }
        return xNumber;
    }
    public static Long god(Long xNumber) {
        if (ConstantType.GOD_USER_NUMBER.equals(xNumber)) {
            return null;
        }
        return xNumber;
    }
    public static boolean isGod(String xNumber) {
        return ConstantType.GOD_USER_NUMBER.toString().equals(xNumber);
    }

    public static boolean isGod() {
        List<RoleDTO> roleDTOS =  CurrentUserUtil.userDetail().getRoleList();
        List<String> roleCodes = roleDTOS.stream().map(RoleDTO::getRoleCode).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(roleCodes)) {
            return false;
        }
        return roleCodes.contains(ConstantType.GOD_ROLE_CODE);
    }
}
