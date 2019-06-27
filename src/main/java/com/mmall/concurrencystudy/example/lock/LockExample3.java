package com.mmall.concurrencystudy.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


@Slf4j

public class LockExample3 {

    private final Map<String,Data> map = new TreeMap<>();

    //定义一个可重入读写锁
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    //读锁
    private final Lock readLock = lock.readLock();
    //写锁
    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        //加上读锁
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            //释放锁
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        //加读锁
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Data put(String key,Data value){
        //加上写锁
        writeLock.lock();
        try {
            return map.put(key,value);
        } finally {
            writeLock.unlock();
        }
    }



    class Data{

    }
}
