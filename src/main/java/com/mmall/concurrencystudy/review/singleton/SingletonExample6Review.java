package com.mmall.concurrencystudy.review.singleton;

import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 饿汉式
 * 单例实例在类装载的时候进行创建
 */
@ThreadSafe
public class SingletonExample6Review {
    // 私有构造函数
    private SingletonExample6Review() {

    }


    // 静态域和静态代码块的执行顺序取决于它们的书写顺序
    // 单例对象
    private static SingletonExample6Review instance = null;


    static {
        instance = new SingletonExample6Review();
    }

    // 静态工厂方法
    public static SingletonExample6Review getInstance() {
        return instance;
    }

    public static void main(String[] args){
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }

}
