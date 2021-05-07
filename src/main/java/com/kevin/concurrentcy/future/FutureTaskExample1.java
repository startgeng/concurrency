package com.kevin.concurrentcy.future;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-07 11:41
 */
@Slf4j
public class FutureTaskExample1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask task  = new FutureTask(()->{
            log.info("do something callable");
            Thread.sleep(10000);
            return "Do";
        });
        new Thread(task).start();
        log.info("do something main");
        Thread.sleep(1000);
        String msg = (String) task.get();
        if (msg.equals("Do")){
            log.info("true");
        }
        log.info("{}",msg);
    }
}
