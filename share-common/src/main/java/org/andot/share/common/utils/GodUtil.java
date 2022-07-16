package org.andot.share.common.utils;

import org.andot.share.common.type.ConstantType;

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
}
