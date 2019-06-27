package com.mmall.concurrencystudy.example.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mmall.concurrencystudy.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ThreadSafe
public class ImmutableExample3 {
    private static final ImmutableList<Integer> list = ImmutableList.of(1,2,3);
    private static final ImmutableSet<Integer> set = ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer,Integer> map1 = ImmutableMap.of(1,2,3,4);

    private static final ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder()
            .put(1,2).put(3,4).put(5,6).build();

    public static void main(String[] args){
        //不可变对象,禁止修改
//        list.add(4);
//        set.add(4);
//        map1.put(1,3);
        log.info("{}",map1.get(1));
    }
}
