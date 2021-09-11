package org.andot.share.app.line.config;

import org.andot.share.app.line.core.ComLineServer;
import org.andot.share.app.line.core.ComLineWebSocketServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author andot
 */
@Component
public class StartupApplication implements ApplicationRunner {

    @Resource
    private ComLineServer comLineServer;
    @Resource
    private ComLineWebSocketServer lineWebSocketServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // lineDotServer.startup();
        lineWebSocketServer.startup();
    }
}
