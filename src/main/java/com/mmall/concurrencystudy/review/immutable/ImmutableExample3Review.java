package com.mmall.concurrencystudy.review.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mmall.concurrencystudy.annotations.ThreadSafe;

@ThreadSafe
public class ImmutableExample3Review {
    private static final ImmutableList<Integer> list = ImmutableList.of(1,2,3);

    private static final ImmutableSet set = ImmutableSet.copyOf(list);

    private static final ImmutableMap<Integer,Integer> map1 = ImmutableMap.of(1,2,3,4);

    private static final ImmutableMap<Integer,Integer> map2 = ImmutableMap.<Integer,Integer>builder()
            .put(1,2).put(3,4).put(5,6).build();

    public static void main(String[] args){
        // 不可变对象,无法修改,运行出错
//        list.add(3);
//        set.add(5);

//        map1.put(1,3);
//        map2.put(5,6);
        System.out.println(map1.get(1));

    }
}
