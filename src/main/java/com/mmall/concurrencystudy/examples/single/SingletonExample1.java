package com.mmall.concurrencystudy.examples.single;

import com.mmall.concurrencystudy.annotations.NotRecommend;
import com.mmall.concurrencystudy.annotations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * 在单线程的时候,没问题,是线程安全的;但在多线程的环境下,第22至第24行会出问题
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造方法
    private SingletonExample1(){}

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态工厂方法
    public static SingletonExample1 getInstance(){
        if(instance==null){
            instance = new SingletonExample1();
        }
        return instance;
    }
}
