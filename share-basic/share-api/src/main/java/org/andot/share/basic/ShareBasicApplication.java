package org.andot.share.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.andot.share.basic"})
public class ShareBasicApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShareBasicApplication.class, args);
    }
}
