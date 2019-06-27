package com.mmall.concurrencystudy.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExample1 {
    private static final Integer a = 1;
    private static final String b = "2";
    private static final Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
//        a = 2;
//        b = "3";
//        map = Maps.newHashMap();
        //非线程安全,我们用final修饰了map.它一经初始化就不可指向别的对象,但是却可以改变它里面的值.
        map.put(1,3);
        log.info("{}",map.get(1));
    }
}
