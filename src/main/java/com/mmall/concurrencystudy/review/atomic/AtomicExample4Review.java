package com.mmall.concurrencystudy.review.atomic;

import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class AtomicExample4Review {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args){
        count.compareAndSet(0,2); // 2
        count.compareAndSet(0,1); // no
        count.compareAndSet(2,4); // 4
        count.compareAndSet(3,7); // no
        count.compareAndSet(4,666); // 666
        log.info("count:{}",count);
    }
}
