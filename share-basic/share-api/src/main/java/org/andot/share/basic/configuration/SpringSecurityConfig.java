package org.andot.share.basic.configuration;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.andot.share.common.components.hander.UserAccessDecisionManager;
import org.andot.share.common.components.hander.UserAccessDeniedHandler;
import org.andot.share.common.components.hander.UserAuthenticationManager;
import org.andot.share.common.components.hander.UserLogoutSuccessHandler;
import org.andot.share.common.components.hander.UserUnAuthenticationHandler;
import org.andot.share.common.components.filter.CORSSecurityFilter;
import org.andot.share.basic.components.JwtAuthenticationFilter;
import org.andot.share.basic.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @Value("${share.publicPath:'/doc.html,/favicon.ico'}")
    private List<String> PUBLIC_PATH;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (CollectionUtils.isEmpty(PUBLIC_PATH)) {
            PUBLIC_PATH = new ArrayList<>();
        }
        PUBLIC_PATH.addAll(Arrays.asList(new String[]{"/login", "/phone/sign", "/v2/api-docs", "/v2/api-docs-ext",
                "/swagger-resources/**", "/webjars/**", "/images/**",
                "/doc.html", "/favicon.ico", "/video/**", "/static/**"}));
        String[] urls = new String[PUBLIC_PATH.size()];
        PUBLIC_PATH.toArray(urls);
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(urls).permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
                .authenticationEntryPoint(userUnAuthenticationHandler);
        http.logout().logoutSuccessHandler(userLogoutSuccessHandler)
                .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .logoutUrl("/logout");
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
