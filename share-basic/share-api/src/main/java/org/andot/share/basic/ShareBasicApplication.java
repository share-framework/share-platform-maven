package org.andot.share.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动器
 * @author andot
 */
@SpringBootApplication(scanBasePackages = {"org.andot.share.basic", "org.andot.share.common"})
public class ShareBasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShareBasicApplication.class, args);
    }
}
