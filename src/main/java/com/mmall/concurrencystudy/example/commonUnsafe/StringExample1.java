package com.mmall.concurrencystudy.example.commonUnsafe;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class StringExample1 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();

    // 模拟并发测试
    public static void main(String[] args) throws Exception{
        //定义一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //定义信号量
        final Semaphore semaphore = new Semaphore(threadTotal);
        //定义计数器闭锁
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i=0;i<clientTotal;i++){
            executorService.execute(() ->{
                try {
                    semaphore.acquire(); // 判断进程是否允许被执行,当达到一定的并发数后,add()有可能被临时阻塞
                    update();
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
        log.info("size:{}",stringBuilder.length());
    }

    private static void update(){
        stringBuilder.append("1");
    }
}