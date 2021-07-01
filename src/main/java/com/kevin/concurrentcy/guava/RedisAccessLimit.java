package com.kevin.concurrentcy.guava;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

/**
 * Redis 限流
 * @author kevin
 * @version 1.0
 * @date 2021-07-02 6:27
 */
@Slf4j
public class RedisAccessLimit {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisScript<Boolean> ratelimitLua;

    /**
     *
     * @param key 业务的可以
     * @param limit 每秒限制通过树
     */
    public void limitAccess(String key,Integer limit){
        Boolean accired = false;

        stringRedisTemplate.execute(
                ratelimitLua, //lua脚本
                Lists.newArrayList(), //Lua 里面key的列表
                limit.toString() //lua value的列表
        );

        if (!accired){
            log.info("you are success is blocked key={}",key);
            throw new RuntimeException("you are access is blocked");
        }
    }
}
