package com.mmall.concurrencystudy.examples.concurrent;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer,Integer> map = new ConcurrentHashMap<>();

    // 模拟并发测试
    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            final int count = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire(); // 判断进程是否允许被执行,当达到一定的并发数后,add()有可能被临时阻塞
                    update(count);
                    semaphore.release(); // 执行完以后,将信号量释放
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 每次顺利执行完上面的一次操作(即顺利调用add()方法)后,计数器的值减1
                countDownLatch.countDown();
            });
        }
        countDownLatch.await(); // 能够保证计数器的值为0时,才执行后面的代码
        //关闭线程池
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    private static void update(int i){
        map.put(i,i);
    }
}
