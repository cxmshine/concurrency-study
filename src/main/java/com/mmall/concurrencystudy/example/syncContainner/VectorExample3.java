package com.mmall.concurrencystudy.example.syncContainner;

import java.util.Iterator;
import java.util.Vector;

/**
 * 如果使用foreach或者迭代器的方式对集合进行遍历,尽量不要在遍历的过程中做remove等相关的更新操作
 * 如果一定要做remove操作,则在遍历的过程中对要remove的数做标记,在循环结束后再移除
 */
public class VectorExample3 {

    //java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> v1){    //foreach
        for(Integer i : v1){
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    //java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> v1){  //iterator
        Iterator<Integer> iterator = v1.iterator();
        while(iterator.hasNext()){
            Integer i = iterator.next();
            if(i.equals(3)){
                v1.remove(i);
            }
        }
    }

    // success
    private static void test3(Vector<Integer> v1){  //普通的for循环
        for(int i=0;i<v1.size();i++){
            if(v1.get(i).equals(3)){
                v1.remove(v1.get(i));
            }
        }
    }

    public static void main(String[] args){
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }

}
