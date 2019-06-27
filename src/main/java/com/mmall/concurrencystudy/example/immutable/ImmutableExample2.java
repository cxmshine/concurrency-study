package com.mmall.concurrencystudy.example.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer,Integer> map = Maps.newHashMap();

    static{
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        // map成为不可变对象
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        //编译不会报错,但运行时报错
        map.put(1,3);
        log.info("{}",map.get(1));
    }
}
