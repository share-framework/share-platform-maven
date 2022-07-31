package org.andot.share.app.line.config;


import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.context.annotation.Configuration;

/**
 * jetcache config
 * @date 2022-07-31 15:48:07
 * @since 1.0.0
 */
@Configuration
@EnableMethodCache(basePackages = {
        "org.andot.share.app.line.service"
})
@EnableCreateCacheAnnotation
public class JetCacheConfig {

}
