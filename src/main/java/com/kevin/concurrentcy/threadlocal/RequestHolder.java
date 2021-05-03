package com.kevin.concurrentcy.threadlocal;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-03 19:13
 */
public class RequestHolder<T> {

    public static final ThreadLocal requestHolder = new ThreadLocal<>();

    public static <T> void add(int id){
        requestHolder.set(id);
    }

    public static <T> void remove(){
        requestHolder.remove();
    }

    public static int get(){
        return (int) requestHolder.get();
    }
}
