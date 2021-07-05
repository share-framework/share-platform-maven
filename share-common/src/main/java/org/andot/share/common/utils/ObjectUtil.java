package org.andot.share.common.utils;

/**
 * 对象工具类
 * @author andot
 */
public class ObjectUtil {
    private ObjectUtil (){}

    /**
     * 判断对象为null
     * 如果是字符串，择偶安段是否为空船
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj){
        if(obj == null){
            return true;
        }
        if(obj instanceof String) {
            if("".equals(String.valueOf(obj))){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断对象为null
     * 如果是字符串，择偶安段是否为空船
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj){
        if(obj != null){
            return true;
        }
        if(obj instanceof String) {
            if(!"".equals(String.valueOf(obj))){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为0
     * @param number
     * @return
     */
    public static boolean isZero(Number number){
        if(number.equals(0)) {
            return true;
        } else {
            return false;
        }
    }
}
