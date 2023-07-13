package org.andot.share.common.utils;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.andot.share.common.components.ShareValueComponent;
import org.andot.share.common.domain.JwtUserDetail;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import org.andot.share.common.exception.TokenExpiredRuntimeException;

import java.util.*;

/**
 * Jwt 工具类
 *
 * @author lucas
 */
@Slf4j
public class JwtUtil {

    /**
     * 去掉Token的固定头 <code>Bearer </code>
     *
     * @param authorization token
     * @return del head token
     */
    public static String getTokenByHeader(String authorization) {
        return Optional.ofNullable(authorization).map(token -> token.replace("Bearer ", "")).orElse("");
    }

    /**
     * 生成jwt token
     *
     * @param userDetail
     * @param secret
     * @param expiration
     * @return
     */
    public static String productJwtToken(JwtUserDetail userDetail, String secret, Long expiration) {
        JwtBuilder jwtBuilder = new DefaultJwtBuilder().signWith(SignatureAlgorithm.HS256, secret);
        return jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .setId(userDetail.getXNumber())
                .setIssuer("share.andot.org")
                .setIssuedAt(Calendar.getInstance().getTime())
                .claim("username", userDetail.getUsername())
                .claim("roles", userDetail.getRoles())
                .claim("permissions", userDetail.getPermissions()).compact();
    }

    public static String productJwtToken2(JwtUserDetail userDetail, String secret, Long expiration) {
        JwtBuilder jwtBuilder = new DefaultJwtBuilder().signWith(SignatureAlgorithm.HS256, secret);
        return jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000 * 2))
                .setId(userDetail.getXNumber())
                .setIssuer("share.andot.org")
                .setIssuedAt(Calendar.getInstance().getTime())
                .claim("username", userDetail.getUsername())
                .claim("roles", userDetail.getRoles())
                .claim("permissions", userDetail.getPermissions()).compact();
    }

    /**
     * 释放token中的对象
     *
     * @param token
     * @param secret
     * @return
     */
    public static JwtUserDetail releaseJwtToken(String token, String secret) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        JwtUserDetail jwtUserDetail = new JwtUserDetail();
        jwtUserDetail.setXNumber(claims.getId());
        jwtUserDetail.setUsername(claims.get("username").toString());
        jwtUserDetail.setRoles((List<String>) claims.get("roles"));
        jwtUserDetail.setPermissions((List<String>) claims.get("permissions"));
        return jwtUserDetail;
    }


    public static boolean isTokenExpired(String token, String secret) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            Date date = claims.getExpiration();
            return date.before(new Date());
        } catch (Exception exception) {
            if (exception instanceof ExpiredJwtException) {
                log.error("token is expired, ", exception);
//                throw new TokenExpiredRuntimeException("登录已失效，请重新登录！", exception);
                return true;
            }
            log.error("token if expired error, ", exception);
//            throw new RuntimeException("请重新登录重试！", exception);
            return true;
        }
    }

    public static boolean isTokenExpired(String token) {
        return isTokenExpired(token, ShareValueComponent.getStaticJwtSecret());
    }

}
