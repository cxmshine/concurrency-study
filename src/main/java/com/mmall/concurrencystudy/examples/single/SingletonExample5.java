package com.mmall.concurrencystudy.examples.single;

import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 懒汉模式 双重同步锁单例模式
 */
@ThreadSafe
public class SingletonExample5 {

    // 私有构造方法
    private SingletonExample5(){}

    // 单例对象 volatile + 双重检测机制 限制指令重排
    private volatile static SingletonExample5 instance = null;

    // 1.memory = allocate() 分配对象的内存空间
    // 2.ctorInstance() 初始化对象
    // 3.instance = memory 设置instance指向刚分配的内存

    // 通过volatile修饰instance,限制指令重排,由原来的非线程安全变为线程安全

    // 静态工厂方法
    public static SingletonExample5 getInstance(){
        if(instance==null){ // 双重检测机制
            synchronized (SingletonExample5.class){ // 同步锁
                if(instance==null){
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
