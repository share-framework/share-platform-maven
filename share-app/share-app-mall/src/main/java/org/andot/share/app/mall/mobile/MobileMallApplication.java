package org.andot.share.app.mall.mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * share mall mobile api
 * @author lucas
 * @date 2020-06-14
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MobileMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobileMallApplication.class, args);
    }
}
