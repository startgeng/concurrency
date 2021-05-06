package com.kevin.concurrentcy.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-06 13:03
 */
@Slf4j
public class CyclicBarrierExample1 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10 ; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            threadPool.execute(()->{
                try {
                    race(threadNum);
                }catch (Exception e){
                    log.info(e+"");
                }
            });
        }
        threadPool.shutdown();
    }

    private static void race(int threadNum) throws BrokenBarrierException, InterruptedException {
        Thread.sleep(1000);
        log.info("{}",threadNum+" 准备中");
        cyclicBarrier.await();
        log.info("{}",threadNum+" 执行完毕");
    }
}
