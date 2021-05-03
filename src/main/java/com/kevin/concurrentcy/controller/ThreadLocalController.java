package com.kevin.concurrentcy.controller;

import com.kevin.concurrentcy.threadlocal.RequestHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-04 1:56
 */
@RestController
public class ThreadLocalController {

    @GetMapping(value = "/threadLocal/test")
    public int test(){
        return RequestHolder.get();
    }
}
