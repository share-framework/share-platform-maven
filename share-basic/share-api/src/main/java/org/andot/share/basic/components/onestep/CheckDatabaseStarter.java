package org.andot.share.basic.components.onestep;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 系统启动的时候检测数据库是否启动，是否正常可以连通
 * @author andot
 */
@Slf4j
@Component
public class CheckDatabaseStarter implements InitializingBean, ServletContextAware {

    @Autowired
    public HikariDataSource hikariDataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("CheckDatabaseStarter#afterPropertiesSet");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        try {
            Connection connection = hikariDataSource.getConnection();
            if(connection == null){
                log.error("数据库连接失败，请查测试数据库是否可以连接！");
                log.error("数据库连接信息：");
                log.error("数据库连接地址：{}", hikariDataSource.getJdbcUrl());
                log.error("数据库用户名：{}", hikariDataSource.getUsername());
                log.error("数据库密码：{}", hikariDataSource.getPassword());
                throw new RuntimeException("请检查数据库连接");
            }
        } catch (SQLException sqlException) {
            throw new RuntimeException("请检查数据库连接:"+sqlException.getMessage());
        }
        log.info("数据库连接成功！");
    }
}
