package org.andot.share.common.components;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Andot
 */
@Component("shareValueComponent")
public class ShareValueComponent {
    @Value("${share.info}")
    private String info;
    @Value("${share.jwt.secret}")
    private String jwtSecret;
    @Value("${share.jwt.expiration}")
    private Long jwtExpiration;

    private static ShareValueComponent that;

    @PostConstruct
    public void init() {
        that = this;
    }

    public String getInfo() {
        return info;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public Long getJwtExpiration() {
        return jwtExpiration;
    }

    public static String getStaticJwtSecret() {
        return that.jwtSecret;
    }
}
