package com.mmall.concurrencystudy.examples.threadLocal;

public class RequestHolder {
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    //往ThreadLocal中放数据
    //通过Filter拦截住请求,然后放数据
    public static void add(Long id){
        requestHolder.set(id);
    }

    //从ThreadLocal中获取数据
    public static Long getId(){
        return requestHolder.get();
    }

    //移除ThreadLocal中的数据
    //及时地将数据移除,才能防止内存泄漏
    public static void remove(){
        requestHolder.remove();
    }
}
