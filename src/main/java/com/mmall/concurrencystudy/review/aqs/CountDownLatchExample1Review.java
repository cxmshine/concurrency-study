package com.mmall.concurrencystudy.review.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchExample1Review {

    private static final int clientTotal = 200;

    public static void main(String[] args) throws Exception{
        // 定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义一个计数器闭锁
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++) {
            final int threadNum = i;
            executorService.execute(()-> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                }finally {
                    // 计数器的值减1
                    countDownLatch.countDown();
                }
            });

        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("finish");
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
        Thread.sleep(100);
    }
}
