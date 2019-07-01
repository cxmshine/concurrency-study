package com.mmall.concurrencystudy.review.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExample2Review {

    private static final Integer a = 1;
    private static final String b = "2";
    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        // 下面这条语句执行过后,map变为不可修改的对象
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args){
        // 运行出错,不可修改map中的key-value
        map.put(1,3);
        log.info("{}",map.get(1));
    }

}