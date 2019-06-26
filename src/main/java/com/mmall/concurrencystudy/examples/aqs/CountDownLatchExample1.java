package com.mmall.concurrencystudy.examples.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchExample1 {

    private final static int threadCount = 200;

    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            executorService.execute(()-> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("exception",e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        //这一语句保证只有当计数器的值减为0时,才执行后面的语句
        countDownLatch.await();
        log.info("finish");
        //关闭线程池
        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        Thread.sleep(10);
        log.info("{}",threadNum);
        Thread.sleep(10);
    }
}
