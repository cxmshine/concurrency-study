package com.mmall.concurrencystudy.review.syncContainer;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class VectorExample1Review {

    private static final int clientTotal = 5000;

    private static final int threadTotal = 200;

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Semaphore semaphore = new Semaphore(threadTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<clientTotal;i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", vector.size());
    }

    private static void add(int i) {
        vector.add(i);
    }
}
