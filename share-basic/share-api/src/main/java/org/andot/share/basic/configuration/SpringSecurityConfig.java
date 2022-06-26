package org.andot.share.basic.configuration;

import org.andot.share.basic.components.UserAccessDecisionManager;
import org.andot.share.basic.components.handler.UserAccessDeniedHandler;
import org.andot.share.basic.components.handler.UserAuthenticationManager;
import org.andot.share.basic.components.handler.UserLogoutSuccessHandler;
import org.andot.share.basic.components.handler.UserUnAuthenticationHandler;
import org.andot.share.basic.components.filter.CORSSecurityFilter;
import org.andot.share.basic.components.filter.JwtAuthenticationFilter;
import org.andot.share.basic.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author lucas
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CORSSecurityFilter corsSecurityFilter;
    @Autowired
    private UserAccessDeniedHandler userAccessDeniedHandler;
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;
    @Autowired
    private UserUnAuthenticationHandler userUnAuthenticationHandler;
    @Autowired
    private UserAccessDecisionManager userAccessDecisionManager;
    @Resource
    private UserServiceImpl userService;
    @Autowired
    private UserAuthenticationManager userAuthenticationManager;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(userAuthenticationManager);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/v2/api-docs", "/v2/api-docs-ext",
                        "/swagger-resources/**", "/webjars/**", "/images/**",
                        "/doc.html", "/favicon.ico", "/video/**", "/static/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().permitAll();
        http.exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
                .authenticationEntryPoint(userUnAuthenticationHandler);
        http.logout().logoutSuccessHandler(userLogoutSuccessHandler)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutUrl("/share/logout");
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsSecurityFilter, JwtAuthenticationFilter.class);
    }

    /**
     * 执行自定义user service
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    /**
     * 防止拦截css,js，image文件
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }
}
