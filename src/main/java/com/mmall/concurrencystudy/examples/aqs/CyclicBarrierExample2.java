package com.mmall.concurrencystudy.examples.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * CountDownLatch是减数,CyclicBarrier是加数.
 * 前者是一次使用,后者可以循环使用.
 */
@Slf4j
public class CyclicBarrierExample2 {

    // 设置同步等待的线程数,这里我设置为5
    private static final CyclicBarrier barrier = new CyclicBarrier(5);

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
        try {
            barrier.await(2000, TimeUnit.MILLISECONDS);    // 核心方法
        } catch (BrokenBarrierException | TimeoutException e) {
            log.warn("BarrierException",e);
        }
        log.info("{} continue",threadNum);

    }
}
