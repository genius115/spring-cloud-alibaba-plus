package com.xiaomai.cloud.test.annotation;

import java.lang.annotation.*;

/**
 *

 **
 * 创建一个限速器，每1秒，产生2.5个令牌
RateLimiter rateLimiter = RateLimiter.create(2.5, 1, TimeUnit.SECONDS);

**
 * 尝试获取1个令牌，如果没有，会阻塞当前线程。直到获取成功返回。
 * 返回值是，阻塞的秒数
 double waitSeconds = rateLimiter.acquire();

**
 * 尝试获取1个令牌，不会阻塞当前线程。
 * 立即返回是否获取成功。
 boolean success = rateLimiter.tryAcquire();

 *
 *
 * @Description 基于Guava限流注解
 * @author Madison
 * @date 2021/4/2
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Limit {
    //资源名称
    String name() default "";

    // 限制每秒访问次数，默认最大即不限制
    double perSecond() default Double.MAX_VALUE;

}
