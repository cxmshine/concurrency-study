package com.mmall.concurrencystudy.review.atomic;

import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe
public class AtomicExample1Review {
    // 用户请求数为5000
    private static final int clientTotal = 5000;

    // 最多允许200个线程同时并发执行
    private static final int threadTotal = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        // 定义线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定义计数器闭锁
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        // 定义信号量
        Semaphore semaphore = new Semaphore(threadTotal);

        for(int i=0;i<clientTotal;i++) {
            executorService.execute(()-> {
                try {
                    // 获取信号量,只有拿到信号量才可以执行后续操作
                    semaphore.acquire();
                    // 并发操作
                    add();
                    // 执行完add()方法后,释放信号量
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 每调用1次add()方法,计数器的值减1
                countDownLatch.countDown();
            });
        }
        // 只有当计数器的值为0时,才会执行后面的代码
        countDownLatch.await();
        // 关闭线程池
        executorService.shutdown();
        log.info("count:{}",count.get());

    }

    private static void add() {
        count.incrementAndGet();
//        count.getAndIncrement()
    }
}
