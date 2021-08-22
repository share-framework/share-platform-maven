package org.andot.share.common.cache;

import org.andot.share.common.domain.JwtUserDetail;

/**
 * 本地线程缓存
 * @author andot
 */
public class LocalThreadCache {
    private static ThreadLocal<JwtUserDetail> jwtUserDetailThreadLocal;

    static {
        jwtUserDetailThreadLocal = new ThreadLocal<>();
    }

    public static JwtUserDetail getUserDetail () {
        return jwtUserDetailThreadLocal.get();
    }

    public static void setUserDetail (JwtUserDetail jwtUserDetail) {
        jwtUserDetailThreadLocal.set(jwtUserDetail);
    }

    public static void remove () {
        jwtUserDetailThreadLocal.remove();
    }


}
