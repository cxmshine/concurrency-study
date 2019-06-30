package com.mmall.concurrencystudy.review.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1Review {
    // 修饰代码块,作用于调用的对象 [如果是两个不同对象同时执行,互不干扰]
    public void test1(int j) {
        // 被synchronized (this) { } 包含在内的代码块叫同步代码块
        synchronized (this) {
            for(int i=0;i<10;i++) {
                log.info("test1 {} - {}",j,i);
            }
        }
    }

    // 修饰方法,作用于调用的对象
    // 直接在方法头加synchronized关键字进行修饰即可
    public synchronized void test2(int j) {
        for(int i=0;i<10;i++) {
            log.info("test2 {} - {}",j,i);
        }
    }

    public static void main(String[] args){
        SynchronizedExample1Review example1 = new SynchronizedExample1Review();
        SynchronizedExample1Review example2 = new SynchronizedExample1Review();
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
