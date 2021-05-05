package com.kevin.concurrentcy.aqs;

import com.kevin.concurrentcy.interfaces.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-05 11:48
 */
@ThreadSafe
@Slf4j
public class SemaphoreExample1 {

    private static final int threadClient = 20;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < threadClient; i++) {
            final int threadNum = i;
            threadPool.execute(()->{
                try {
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                        test(threadNum);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        threadPool.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
