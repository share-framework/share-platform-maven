package org.andot.share.basic.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.andot.share.basic.dao")
public class MyBatisConfiguration {
}
