package com.mmall.concurrencystudy.review.syncContainer;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;

import java.util.Iterator;
import java.util.Vector;


@NotThreadSafe
public class VectorExample3Review {

    // java.util.ConcurrentModificationException
    private static void test1(Vector<Integer> vector) { // foreach
        for(Integer i : vector) {
            if(i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    // java.util.ConcurrentModificationException
    private static void test2(Vector<Integer> vector) {
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if(i.equals(3)) {
                vector.remove(i);
            }
        }
    }

    // ok
    private static void test3(Vector<Integer> vector) {
        for(int i=0;i<vector.size();i++) {
            if(vector.get(i).equals(3)) {
                vector.remove(vector.get(i));
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
