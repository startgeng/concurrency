package com.kevin.concurrentcy.datesafedealwith;

import com.kevin.concurrentcy.interfaces.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-04 3:11
 */
@Slf4j
@NoThreadSafe
public class DateTimeFormateExample1 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

//    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int k = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    update(k);
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void update(int k) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        log.info("date->{},{}",k,date);
    }
}
