package com.mmall.concurrencystudy.review.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample2Review {

    private static final int clientTotal = 20;

    // 允许最大并发数为3
    private static final int threadTotal = 3;

    public static void main(String[] args) throws Exception{
        // 定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义一个信号量
        Semaphore semaphore = new Semaphore(threadTotal);
        // 定义一个计数器闭锁
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++) {
            final int threadNum = i;
            executorService.execute(()-> {
                try {
                    semaphore.acquire(3);
                    test(threadNum);
                    semaphore.release(3);
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
        log.info("{}",threadNum);
        Thread.sleep(1000);
    }
}
