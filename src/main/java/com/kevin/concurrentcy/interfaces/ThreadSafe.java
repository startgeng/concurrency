package com.kevin.concurrentcy.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author kevin
 * @version 1.0
 * @date 2021-05-04 2:49
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";
}
