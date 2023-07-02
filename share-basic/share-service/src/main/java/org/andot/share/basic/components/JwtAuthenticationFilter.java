package org.andot.share.basic.components;

import lombok.extern.slf4j.Slf4j;
import org.andot.share.common.components.ShareValueComponent;
import org.andot.share.common.cache.LocalThreadCache;
import org.andot.share.common.components.exception.TokenErrorServletException;
import org.andot.share.common.domain.JwtUserDetail;
import org.andot.share.common.utils.JwtUtil;
import org.andot.share.basic.service.impl.UserServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * jwt 认证过滤器
 *
 * @author lucas
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Resource
    private ShareValueComponent shareValueComponent;
    @Resource
    private UserServiceImpl userService;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = JwtUtil.getTokenByHeader(request.getHeader("X-Token"));
        if (!StringUtils.isEmpty(token) && !token.equalsIgnoreCase("undefined")) {
            if (JwtUtil.isTokenExpired(token, shareValueComponent.getJwtSecret())) {
                throw new TokenErrorServletException("登录信息已经失效，请重新登录");
            }
            JwtUserDetail jwtUserDetail = JwtUtil.releaseJwtToken(token, shareValueComponent.getJwtSecret());
            LocalThreadCache.setUserDetail(jwtUserDetail);
            UserDetails userDetail = userService.loadUserByUsername(jwtUserDetail.getXNumber());
            if (jwtUserDetail.getXNumber() != null && userDetail != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail,
                        null, jwtUserDetail.getRoles().stream().map(item -> new SimpleGrantedAuthority("ROLE_" + item)).collect(Collectors.toList()));
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
