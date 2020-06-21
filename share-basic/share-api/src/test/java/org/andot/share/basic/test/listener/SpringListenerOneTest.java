package org.andot.share.basic.test.listener;

import lombok.extern.slf4j.Slf4j;
import org.andot.share.basic.controller.UserController;
import org.andot.share.basic.test.event.SpringEventOneTest;
import org.andot.share.basic.test.event.SpringEventTwoTest;
import org.springframework.context.ApplicationListener;

@Slf4j
public class SpringListenerOneTest implements ApplicationListener<SpringEventTwoTest> {
    @Override
    public void onApplicationEvent(SpringEventTwoTest event) {
        log.info(event.getSource().getClass().getName());
        System.err.println("收到了");
    }
}
