package com.icezhg.sunflower.annotation;


import com.icezhg.sunflower.enums.OperationType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhongjibing on 2022/12/23.
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Operation {
    String title();

    OperationType type();

    boolean saveParameter() default true;

    boolean saveResult() default true;
}
