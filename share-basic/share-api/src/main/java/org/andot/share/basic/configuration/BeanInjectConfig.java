package org.andot.share.basic.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Bean注入配置
 *
 * @author lucas
 */
@Configuration
public class BeanInjectConfig {
    /**
     * 解决返回中文的问题
     *
     * @return
     */

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
