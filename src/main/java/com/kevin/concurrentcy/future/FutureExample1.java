package com.kevin.concurrentcy.future;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.*;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-07 11:30
 */
@Slf4j
public class FutureExample1 {

    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something callable");
            Thread.sleep(5000);
            return "Do";
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{
            Future<String> future = threadPool.submit(new MyCallable());
            log.info("do something main");
            Thread.sleep(1000);
            String msg = future.get();
            log.info("{}",msg);
        }finally {
            threadPool.shutdown();
        }
    }
}


