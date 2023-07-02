package org.andot.share.app.line;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Linedot启动器
 *
 * LineDot 同道
 * 取自 some line 是 同一行
 *  dot 音译 道
 * @author andot
 */
@SpringBootApplication(scanBasePackages = {
        "org.andot.share.app.line",
        "org.andot.share.common",
        "org.andot.share.basic"})
public class ComLineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComLineApplication.class, args);
    }

}
