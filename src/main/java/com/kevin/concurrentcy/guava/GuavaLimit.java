package com.kevin.concurrentcy.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-07-01 22:08
 * guava 限流
 */
@Slf4j
public class GuavaLimit {

    //每秒发放2个通行证
    RateLimiter limiter = RateLimiter.create(2.0);

    //非阻塞限流
    public String tryAcquire(Integer count){
        //每秒消耗一张通行证
        if (limiter.tryAcquire(count)){
            log.info("success,rate is {}",limiter.getRate());
            return "success";
        }else {
            log.info("fail,rate is {}",limiter.getRate());
            return "fail";
        }
    }

    /**
     *  非阻塞时间
     * @param count
     * @return
     */
    public String tryAcquireWithTimeout(Integer count,Integer timeout){
        //每秒消耗count张通行证 timeout会挂起服务的时间 count = 10 , timeout = 5 挂起在5秒以内
        if (limiter.tryAcquire(count,timeout, TimeUnit.SECONDS)){
            log.info("success,rate is {}",limiter.getRate());
            return "success";
        }else {
            log.info("fail,rate is {}",limiter.getRate());
            return "fail";
        }
    }
}
