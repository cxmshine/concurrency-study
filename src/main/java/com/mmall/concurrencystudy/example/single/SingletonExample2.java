package com.mmall.concurrencystudy.example.single;

import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载使用时进行创建
 * 缺点为:若构造方法中有过多的处理,类加载很慢,则性能差.
 */
@ThreadSafe
public class SingletonExample2 {

    // 私有构造方法
    private SingletonExample2(){}

    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    // 静态工厂方法
    public static SingletonExample2 getInstance(){
        return instance;
    }
}
