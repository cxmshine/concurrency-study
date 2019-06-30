package com.mmall.concurrencystudy.review.singleton;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;

/**
 * 懒汉式
 * 实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample1Review {
    // 私有构造函数
    private SingletonExample1Review() {

    }

    // 单例对象
    private static SingletonExample1Review instance = null;

    // 静态工厂方法
    public static SingletonExample1Review getInstance() {
        if(instance==null) {
            instance = new SingletonExample1Review();
        }
        return instance;
    }

}
