package com.mmall.concurrencystudy.review.singleton;

import com.mmall.concurrencystudy.annotations.Recommend;
import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 枚举模式 : 最安全
 * 暂未掌握
 */
@ThreadSafe
@Recommend
public class SingletonExample7Review {
    // 私有构造函数
    private SingletonExample7Review() {

    }

    public static SingletonExample7Review getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    // 枚举
    private enum Singleton {
        INSTANCE;

        private SingletonExample7Review singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7Review();
        }

        public SingletonExample7Review getInstance() {
            return singleton;
        }
    }
}
