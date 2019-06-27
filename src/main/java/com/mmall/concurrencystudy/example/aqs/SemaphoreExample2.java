package com.mmall.concurrencystudy.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 观察输出结果,会觉得跟单线程的执行结果一样
 * 因为最大允许并发数为3,每次获取3个许可,释放3个许可.
 */
@Slf4j
public class SemaphoreExample2 {

    private final static int threadCount = 20;
    
    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 定义一个信号量,最大允许并发数为3
        final Semaphore semaphore = new Semaphore(3);

        for(int i=0;i<threadCount;i++){
            final int threadNum = i;
            executorService.execute(()-> {
                try {
                    semaphore.acquire(3); // 获取多个许可
                    test(threadNum);
                    semaphore.release(3); // 释放多个许可
                } catch (Exception e) {
                    log.error("exception",e);
                }
            });
        }
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws Exception{
        log.info("{}",threadNum);
        Thread.sleep(100);
    }
}