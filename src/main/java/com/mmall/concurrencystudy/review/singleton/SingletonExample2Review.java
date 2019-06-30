package com.mmall.concurrencystudy.review.singleton;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 饿汉式
 * 单例实例在类装载的时候进行创建
 */
@ThreadSafe
public class SingletonExample2Review {
    // 私有构造函数
    private SingletonExample2Review() {

    }

    // 单例对象
    private static SingletonExample2Review instance = new SingletonExample2Review();

    // 静态工厂方法
    public static SingletonExample2Review getInstance() {
        return instance;
    }

}
