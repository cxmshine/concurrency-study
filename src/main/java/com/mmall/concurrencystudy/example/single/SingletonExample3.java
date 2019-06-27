package com.mmall.concurrencystudy.example.single;

import com.mmall.concurrencystudy.annotations.NotRecommend;
import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 对SingletonExample1做了改进
 * 使用synchronized关键字修饰工厂方法,实现了线程安全,但这仍然是不推荐的,因为会带来额外的性能开销
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有构造方法
    private SingletonExample3(){}

    // 单例对象
    private static SingletonExample3 instance = null;

    // 静态工厂方法
    public synchronized static SingletonExample3 getInstance(){
        if(instance==null){
            instance = new SingletonExample3();
        }
        return instance;
    }
}
