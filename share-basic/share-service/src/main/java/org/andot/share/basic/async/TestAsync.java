package org.andot.share.basic.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("testAsync")
public class TestAsync {
    @Async
    public String test(String name){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("任务执行完毕"+name);
        return name;
    }
}
