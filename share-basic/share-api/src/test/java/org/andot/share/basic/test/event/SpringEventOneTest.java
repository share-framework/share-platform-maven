package org.andot.share.basic.test.event;

import org.springframework.context.ApplicationEvent;

public class SpringEventOneTest extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public SpringEventOneTest(Object source) {
        super(source);
    }
}
