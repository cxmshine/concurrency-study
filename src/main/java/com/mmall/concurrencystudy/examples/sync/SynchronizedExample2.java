package com.mmall.concurrencystudy.examples.sync;

import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@ThreadSafe
public class SynchronizedExample2 {

    // 修饰一个类
    public static void test1(int j){
        synchronized (SynchronizedExample2.class){      //修饰这个类,原先是修饰调用此方法的对象
            for(int i=0;i<10;i++){
                log.info("test1 {} - {}",j,i);
            }
        }
    }


    public static synchronized void test2(int j){
        for(int i=0;i<10;i++){
            log.info("test2 {} - {}",j,i);
        }
    }

    public static void main(String[] args){
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test1(1);
        });
        executorService.execute(() -> {
            example2.test1(2);
        });
    }
}
