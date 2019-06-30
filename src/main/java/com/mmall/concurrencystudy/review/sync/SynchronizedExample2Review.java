package com.mmall.concurrencystudy.review.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2Review {
    // 修饰类
    public static void test1(int j) {
        synchronized (SynchronizedExample2Review.class) {
            for(int i=0;i<10;i++) {
                log.info("test1 {} - {}",j,i);
            }
        }
    }

    // 修饰静态方法,作用于所有对象
    public static synchronized void test2(int j) {
        for(int i=0;i<10;i++) {
            log.info("test2 {} - {}",j,i);
        }
    }

    public static void main(String[] args){
        SynchronizedExample2Review example1 = new SynchronizedExample2Review();
        SynchronizedExample2Review example2 = new SynchronizedExample2Review();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(()->{
            example1.test2(1);
        });
        executorService.execute(()->{
            example2.test2(2);
        });
        executorService.shutdown();
    }
}
