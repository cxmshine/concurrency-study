package com.mmall.concurrencystudy.review.atomic;

import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample5Review {

    private static AtomicIntegerFieldUpdater<AtomicExample5Review> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5Review.class,"count");

    // 该变量必须是非静态的、被volatile修饰的
    @Getter
    private volatile int count = 100;

    public static void main(String[] args){
        AtomicExample5Review example5 = new AtomicExample5Review();
        if(updater.compareAndSet(example5,100,120)) {
            log.info("update success 1, {}",example5.getCount());
        }
        if(updater.compareAndSet(example5,100,120)) {
            log.info("update success 2, {}",example5.getCount());
        }else {
            log.info("update failed, {}",example5.getCount());
        }
    }
}
