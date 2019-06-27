package com.mmall.concurrencystudy.example.atomic;

import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicReferenceFieldUpdater
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {

    //原子性更新AtomicExample5这个类的一个实例中名为"count"的这个字段,该字段必须被volatile修饰,且不能被static修饰
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater =
            AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args){
        AtomicExample5 example5 = new AtomicExample5();
        if(updater.compareAndSet(example5,100,120)){
            log.info("update success 1,{}",example5.getCount());
        }

        if(updater.compareAndSet(example5,100,120)){
            log.info("update success 2,{}",example5.getCount());
        }else{
            log.info("update failed,{}",example5.getCount());
        }
    }

}
