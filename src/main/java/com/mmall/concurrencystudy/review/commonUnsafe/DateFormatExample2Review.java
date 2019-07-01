package com.mmall.concurrencystudy.review.commonUnsafe;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class DateFormatExample2Review {

    private static final int clientTotal = 5000;

    private static final int threadTotal = 200;

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Semaphore semaphore = new Semaphore(threadTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<clientTotal;i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    parse();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    // 通过【堆栈封闭】的方式保证线程安全
    private static void parse() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            simpleDateFormat.parse("20190701");
        } catch (ParseException e) {
            log.error("parse exception",e);
        }
    }
}
