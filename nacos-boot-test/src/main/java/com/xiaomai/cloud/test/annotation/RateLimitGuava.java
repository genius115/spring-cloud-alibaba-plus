package com.xiaomai.cloud.test.annotation;

import java.lang.annotation.*;

/**
 * @author Madison
 * @date 2021/4/2
 */
/**
 * 自定义注解可以不包含属性，成为一个标识注解
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimitGuava {

}