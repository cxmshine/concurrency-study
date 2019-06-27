package com.mmall.concurrencystudy.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample3 {

    // 设置同步等待的线程数,这里我设置为5
    // 与Example1相比,增加了第二个参数.意为:在屏障打开时,优先执行此处的方法
    private static final CyclicBarrier barrier = new CyclicBarrier(5,() -> {
        log.info("callback is running");
    });

    public static void main(String[] args) throws Exception{

        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<10;i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        barrier.await();    // 核心方法
        log.info("{} continues",threadNum);

    }
}
