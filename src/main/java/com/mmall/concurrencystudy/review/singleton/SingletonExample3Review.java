package com.mmall.concurrencystudy.review.singleton;

import com.mmall.concurrencystudy.annotations.NotRecommend;
import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 懒汉式改进
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3Review {
    // 私有构造函数
    private SingletonExample3Review() {

    }

    // 单例对象
    private static SingletonExample3Review instance = null;

    // 静态工厂方法
    // 使用synchronized能够保证线程安全,但不推荐这么使用,因为会增加开销,性能不好
    public synchronized static SingletonExample3Review getInstance() {
        if(instance==null) {
            instance = new SingletonExample3Review();
        }
        return instance;
    }

}
