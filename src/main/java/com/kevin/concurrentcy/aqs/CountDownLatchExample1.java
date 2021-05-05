package com.kevin.concurrentcy.aqs;

import com.kevin.concurrentcy.interfaces.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-05 10:49
 */
@ThreadSafe
@Slf4j
public class CountDownLatchExample1 {

    private static final int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadTotal);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadTotal; i++) {
            final int threadNum = i;
            threadPool.execute(()->{
                try {
                    test(threadNum);
                }catch (Exception e){
                    log.info(e+"");
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        log.info("finsh");
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        threadPool.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
