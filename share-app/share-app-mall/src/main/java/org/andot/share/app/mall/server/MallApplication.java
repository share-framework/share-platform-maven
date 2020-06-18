package org.andot.share.app.mall.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * share mall server pc api
 * @author lucas
 * @date 2020-06-14
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(MallApplication.class, args);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
