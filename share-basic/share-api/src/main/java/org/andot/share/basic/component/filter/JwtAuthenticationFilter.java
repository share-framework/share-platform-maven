package org.andot.share.basic.component.filter;

import lombok.extern.slf4j.Slf4j;
import org.andot.share.basic.component.ShareValueComponent;
import org.andot.share.basic.component.exception.TokenErrorServletException;
import org.andot.share.common.domain.JwtUserDetail;
import org.andot.share.common.utils.JwtUtil;
import org.andot.share.basic.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

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

    @Autowired
    private ShareValueComponent shareValueComponent;
    @Resource
    private UserServiceImpl userService;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String token = JwtUtil.getTokenByHeader(request.getHeader("X-Token"));
        if (!StringUtils.isEmpty(token)) {
            if (JwtUtil.isTokenExpired(token, shareValueComponent.getJwtSecret())) {
                throw new TokenErrorServletException("登录信息已经失效，请重新登录");
            }
            JwtUserDetail jwtUserDetail = JwtUtil.releaseJwtToken(token, shareValueComponent.getJwtSecret());
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
