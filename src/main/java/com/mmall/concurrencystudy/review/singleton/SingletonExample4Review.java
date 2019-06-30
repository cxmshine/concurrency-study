package com.mmall.concurrencystudy.review.singleton;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;

/**
 * 懒汉式 => 双重检测机制
 * 实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample4Review {
    // 私有构造函数
    private SingletonExample4Review() {

    }

    // 单例对象
    private static SingletonExample4Review instance = null;

    // 静态工厂方法
    public static SingletonExample4Review getInstance() {
        if(instance==null) {    // 双重检测机制
            synchronized (SingletonExample4Review.class) {  // 同步锁
                if(instance==null) {
                    instance = new SingletonExample4Review();
                }
            }
        }
        return instance;
    }

}
