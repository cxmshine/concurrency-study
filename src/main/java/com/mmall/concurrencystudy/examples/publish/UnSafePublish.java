package com.mmall.concurrencystudy.examples.publish;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnSafePublish {
    private String[] states = {"a","b","c"};

    public String[] getStates(){
        return states;
    }

    public static void main(String[] args){
        UnSafePublish unSafePublish = new UnSafePublish();
        String[] states = unSafePublish.getStates();
        log.info("{}", Arrays.toString(states));

        states[0] = "d";
        log.info("{}",Arrays.toString(states));
    }
}
