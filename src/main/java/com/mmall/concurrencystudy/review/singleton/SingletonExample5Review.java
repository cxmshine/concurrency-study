package com.mmall.concurrencystudy.review.singleton;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 懒汉式 => 双重检测机制
 * 实例在第一次使用时进行创建
 */
@ThreadSafe
public class SingletonExample5Review {
    // 私有构造函数
    private SingletonExample5Review() {

    }

    // volatile + 双重检测机制 => 禁止指令重排
    private volatile static SingletonExample5Review instance = null;

    // 静态工厂方法
    public static SingletonExample5Review getInstance() {
        if(instance==null) {    // 双重检测机制
            synchronized (SingletonExample5Review.class) {  // 同步锁
                if(instance==null) {
                    instance = new SingletonExample5Review();
                }
            }
        }
        return instance;
    }

}
