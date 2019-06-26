package com.mmall.concurrencystudy.examples.concurrent;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ArrayList -> CopyOnWriteArrayList
 * 关于CopyOnWriteArrayList:写操作时复制,当有新元素添加到集合中时,它先从原集合中拷贝一份出来,在新的集合上
 * 做写操作,做完之后再将旧的集合指向新的集合.它的整个写操作是在锁的保护下进行的.
 * 缺点:1.由于要拷贝,所以消耗内存,当集合比较大时,GC;
 *     2.不能用于实时读的场景
 * 更适合【读多写少】的场景
 * 读写分离、最终一致性、使用时另外开辟空间;读操作在原集合上进行,不需要加锁;写操作的时候是要加锁的.
 */
@Slf4j
@ThreadSafe
public class CopyOnWriteArrayListExample {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<>();

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
        log.info("size:{}",list.size());
    }

    private static void update(int i){
        list.add(i);
    }
}
