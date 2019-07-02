package com.mmall.concurrencystudy.review.lock;

import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@ThreadSafe
public class LockExample3Review {
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    private final Map<String,Data> map = new TreeMap<>();

    public Data get(String key) {
        // 读操作,加读锁
        readLock.lock();
        try {
            return map.get(key);
        }finally {
            // 释放锁
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        // 读操作,加读锁
        readLock.lock();
        try {
            return map.keySet();
        }finally {
            // 释放锁
            readLock.unlock();
        }
    }

    public void put(String key,Data value) {
        // 写操作,加上写锁
        writeLock.lock();
        try {
            map.put(key,value);
        }finally {
            writeLock.unlock();
        }
    }

    class Data {

    }



}
