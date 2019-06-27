package com.mmall.concurrencystudy.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        // 延迟3秒执行
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("schedule run");
//            }
//        },3, TimeUnit.SECONDS);

        // 延迟1秒后,每3秒执行1次 【要把下方的关闭线程池的语句注释掉】
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("schedule run");
            }
        },1,3,TimeUnit.SECONDS);

//        executorService.shutdown();

        // Timer也可以做定时任务,下面的程序表示每5秒执行1次
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("timer run");
            }
        },new Date(),5*1000);
    }
}
