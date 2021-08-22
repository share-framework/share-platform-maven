package org.andot.share.basic.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 额外包扫描
 * @author andot
 */
@Configuration
@ComponentScan("org.andot.share.core")
public class PackageScanConfig {
}
