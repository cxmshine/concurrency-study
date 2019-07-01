package com.mmall.concurrencystudy.review.commonUnsafe;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class HashMapExampleReview {

    private static final int clientTotal = 5000;

    private static final int threadTotal = 200;

    private static Map<Integer,Integer> map = new HashMap();

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
        log.info("size:{}", map.size());
    }

    private static void add(int i) {
        map.put(i,i);
    }
}
