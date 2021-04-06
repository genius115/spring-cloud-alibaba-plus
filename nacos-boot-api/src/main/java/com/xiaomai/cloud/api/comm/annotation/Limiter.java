package com.xiaomai.cloud.api.comm.annotation;

/**
 * @author Madison
 * @date 2021/4/6
 */
import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Limiter {
    double LimitNum() default  10;      //默认每秒产生10个令牌
}

