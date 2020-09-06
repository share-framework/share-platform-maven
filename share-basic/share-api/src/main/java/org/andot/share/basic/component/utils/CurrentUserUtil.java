package org.andot.share.basic.component.utils;

import org.andot.share.basic.dto.XUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
}
