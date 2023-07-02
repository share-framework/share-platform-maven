package org.andot.share.app.line.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScans({
    @MapperScan("org.andot.share.app.line.mapper"),
    @MapperScan("mybatis.mapper")
})
public class ImMyBatisConfiguration {
}
