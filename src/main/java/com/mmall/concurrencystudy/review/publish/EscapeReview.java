package com.mmall.concurrencystudy.review.publish;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EscapeReview {

    private int thisCanBeEscape = 0;

    public EscapeReview() {
        new InnerClass();
    }

    private class InnerClass {
        public InnerClass() {
            log.info("{}",EscapeReview.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args){
        new EscapeReview();
    }
}
