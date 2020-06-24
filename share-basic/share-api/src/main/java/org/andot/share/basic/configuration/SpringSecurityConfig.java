package org.andot.share.basic.configuration;

import org.andot.share.basic.component.UserAccessDecisionManager;
import org.andot.share.basic.component.handler.UserAccessDeniedHandler;
import org.andot.share.basic.component.handler.UserLogoutSuccessHandler;
import org.andot.share.basic.component.handler.UserUnAuthenticationHandler;
import org.andot.share.basic.component.filter.CORSSecurityFilter;
import org.andot.share.basic.component.filter.JwtAuthenticationFilter;
import org.andot.share.basic.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/v2/api-docs", "/v2/api-docs-ext",
                        "/swagger-resources/**", "/webjars/**", "/images/**",
                        "/doc.html", "/favicon.ico").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().permitAll();//.authenticated();
        http.exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
                .authenticationEntryPoint(userUnAuthenticationHandler);
        http.logout().logoutSuccessHandler(userLogoutSuccessHandler);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(corsSecurityFilter, JwtAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
