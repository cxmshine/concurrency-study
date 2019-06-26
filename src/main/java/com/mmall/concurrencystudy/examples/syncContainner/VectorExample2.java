package com.mmall.concurrencystudy.examples.syncContainner;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;

import java.util.List;
import java.util.Vector;

/**
 * 这个例子演示了:同步容器!=线程安全
 */
@NotThreadSafe
public class VectorExample2 {

    private static List<Integer> vector = new Vector<>();

    public static void main(String[] args){
        while(true) {
            //初始化vector
            for(int i=0;i<10;i++){
                vector.add(i);
            }

            Thread thread1 = new Thread() {
                public void run(){
                    for(int i=0;i<vector.size();i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2 = new Thread() {
                public void run(){
                    for(int i=0;i<vector.size();i++){
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
