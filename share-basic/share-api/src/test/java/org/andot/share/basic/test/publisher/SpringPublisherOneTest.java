package org.andot.share.basic.test.publisher;

import org.andot.share.basic.test.event.SpringEventOneTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringPublisherOneTest {
    public void publish(AnnotationConfigApplicationContext annotationConfigApplicationContext){
        annotationConfigApplicationContext.publishEvent(new SpringEventOneTest(this));
    }
}
