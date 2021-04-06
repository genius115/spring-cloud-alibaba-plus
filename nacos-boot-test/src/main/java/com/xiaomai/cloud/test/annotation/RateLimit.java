package com.xiaomai.cloud.test.annotation;

import java.lang.annotation.*;

/**
 * @author Madison
 * @date 2021/4/2
 */
@Inherited
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

    double limitNum() default 20;  //速率

}
