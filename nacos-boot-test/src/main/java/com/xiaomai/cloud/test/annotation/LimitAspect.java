package com.xiaomai.cloud.test.annotation;

import com.google.common.util.concurrent.RateLimiter;
import com.xiaomai.cloud.test.exception.LimitAccessException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Description 基于Guava限流
 * @author Madison
 * @date 2021/4/2
 */
@Slf4j
@Aspect
@Component
public class LimitAspect {
    private final static Logger logger = LoggerFactory.getLogger(LimitAspect.class);


    RateLimiter rateLimiter = RateLimiter.create(Double.MAX_VALUE);

    /**
     * 定义切点
     * 1、通过扫包切入
     * 2、带有指定注解切入
     */
//    @Pointcut("execution(public * com.xiaomai.cloud.test.*.*(..))")
    @Pointcut("@annotation(com.xiaomai.cloud.test.annotation.Limit)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        logger.info("拦截到了{}方法...", point.getSignature().getName());

        //获取目标方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Limit limit = method.getAnnotation(Limit.class);
        rateLimiter.setRate(limit.perSecond());

        // 获取令牌桶中的一个令牌，最多等待1秒
        if (rateLimiter.tryAcquire(1, 1, TimeUnit.SECONDS)) {
            return point.proceed();
        } else {
            log.error(limit.name() + " 接口并发量过大执行限流");
            throw new LimitAccessException("网络异常，请稍后重试！");
        }
    }
}
