package com.mmall.concurrencystudy.review.publish;

import com.mmall.concurrencystudy.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublishReview {

    private String[] states = {"a","b","c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args){
        UnsafePublishReview unsafePublishReview = new UnsafePublishReview();
        log.info(Arrays.toString(unsafePublishReview.getStates()));

        unsafePublishReview.getStates()[0] = "d";
        log.info(Arrays.toString(unsafePublishReview.getStates()));
    }
}
