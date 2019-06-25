package com.mmall.concurrencystudy.examples.single;

import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 饿汉模式
 */
@ThreadSafe
public class SingletonExample6 {

    // 私有构造方法
    private SingletonExample6(){}

    // 单例对象
    private static SingletonExample6 instance = null;

    // 此处的静态代码块必须写在上面的这个静态域的后面
    // 静态域和静态代码块是按顺序执行的 (牛客网做过这种题)
    static {
        instance = new SingletonExample6();
    }

    // 静态工厂方法
    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args){
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
