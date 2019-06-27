package com.mmall.concurrencystudy.example.single;

import com.mmall.concurrencystudy.annotations.Recommend;
import com.mmall.concurrencystudy.annotations.ThreadSafe;

/**
 * 枚举模式,最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有构造方法
    private SingletonExample7(){}

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    // 定义一个内部枚举类
    private enum Singleton{
        INSTANCE;

        private SingletonExample7 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }


}
