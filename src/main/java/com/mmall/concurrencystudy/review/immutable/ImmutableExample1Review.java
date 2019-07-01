package com.mmall.concurrencystudy.review.immutable;

import com.google.common.collect.Maps;
import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
@NotThreadSafe
public class ImmutableExample1Review {

    private static final Integer a = 1;
    private static final String b = "2";
    private static final Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args){
//        a=2;
//        b="3";

        // 不可指向新的对象,但可以修改
//        map = Maps.newHashMap();
        map.put(1,3);
        log.info("{}",map.get(1));
    }

    /*
        我之前一直以为final是无法修饰形参的,但实际上它是可以的
        private void set(final int a) {
            a = 2;
        }
   */
}