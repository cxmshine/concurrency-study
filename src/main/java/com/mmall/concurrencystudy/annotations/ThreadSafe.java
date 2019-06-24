package com.mmall.concurrencystudy.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 课程里用来标记线程安全的类或写法
 */
@Target(ElementType.TYPE)   //该注解作用在类上
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {
}
