package com.mmall.concurrencystudy.review.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CyclicBarrierExample3Review {

    // 5个线程都到达屏障时,优先执行此处的代码,然后再执行await()后面的代码
    private static final CyclicBarrier barrier = new CyclicBarrier(5,()-> {
        log.info("callback is running");
    });

    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                }catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        executorService.shutdown();
    }

    private static void race(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{} is ready",threadNum);
        barrier.await();
        log.info("{} is continue",threadNum);
    }
}
