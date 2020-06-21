package org.andot.share.basic.test;

import org.andot.share.basic.test.event.SpringEventOneTest;
import org.andot.share.basic.test.listener.SpringListenerOneTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringEventTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(SpringListenerOneTest.class, SpringEventTest.class);
        context.refresh();
        SpringEventTest springEventTest = context.getBean(SpringEventTest.class);
        springEventTest.publish(context, springEventTest);
        context.close();
    }

    public void publish(ApplicationContext applicationContext, Object object){
        applicationContext.publishEvent(new SpringEventOneTest(object));
    }
}
