package com.mmall.concurrencystudy.review.syncContainer;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;

import java.util.Vector;

/**
 * 同步容器并不是在任何情景下都是线程安全的
 */
@NotThreadSafe
public class VectorExample2Review {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args){
        while (true) {
            for(int i=0;i<10;i++) {
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    for(int i=0;i<vector.size();i++) {
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                @Override
                public void run() {
                    for(int i=0;i<vector.size();i++) {
                        vector.get(i);
                    }
                }
            };

            thread1.start();
            thread2.start();
        }
    }
}
