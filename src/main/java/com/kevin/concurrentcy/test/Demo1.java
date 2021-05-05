package com.kevin.concurrentcy.test;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-04 3:57
 */
@Slf4j
public class Demo1 {

    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put(1,2);
        map.put(1,3);
        map.put(null,null);
        log.info(map.get(1)+" "+ map.size());
    }
}
