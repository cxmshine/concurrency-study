package com.mmall.concurrencystudy.examples.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    //synchronized修饰代码块
    //作用于调用该方法的对象,同一个类的不同实例之间是互不影响的.
    public void test1(int j){
        synchronized (this){        //this表示调用此方法的对象
            for(int i=0;i<10;i++){
                log.info("test1 {} - {}",j,i);
            }
        }
    }

    //synchronized修饰方法
    //试一下将synchronized去除,观察控制台的输出结果,体会体会加与不加的区别.
    public synchronized void test2(int j){
        for(int i=0;i<10;i++){
            log.info("test2 {} - {}",j,i);
        }
    }

    public static void main(String[] args){
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
    }
}
