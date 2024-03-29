package org.andot.share.core.util;

import org.andot.share.core.dto.XUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 当前用户工具类
 * @author andot
 */
public class CurrentUserUtil {
    public static Long getUserCode() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        XUserDetail securityUserDetail = (XUserDetail) authentication.getPrincipal();
        return securityUserDetail.getUser().getXNumber();
    }

    public static String getUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        XUserDetail securityUserDetail = (XUserDetail) authentication.getPrincipal();
        return securityUserDetail.getUsername();
    }

    public static Long getAppId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        XUserDetail securityUserDetail = (XUserDetail) authentication.getPrincipal();
        return securityUserDetail.getUser().getAppId();
    }

    public static XUserDetail userDetail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return null;
        }
        XUserDetail securityUserDetail = (XUserDetail) authentication.getPrincipal();
        return securityUserDetail;
    }
}
