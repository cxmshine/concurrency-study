package com.mmall.concurrencystudy.review.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2Review {

    private static final int clientTotal = 200;

    public static void main(String[] args) throws Exception{
        // 定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义一个计数器闭锁
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++) {
            final int threadNum = i;
            executorService.execute(()->{
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
        // 等待10毫秒就执行下面的代码,而不是一直等到计数器的值为0
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        executorService.shutdown();
        // 与example1相比,这里会先打印finish
        log.info("finish");
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(100);
        log.info("{}",threadNum);
    }
}
