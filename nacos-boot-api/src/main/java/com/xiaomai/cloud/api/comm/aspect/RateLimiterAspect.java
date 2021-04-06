package com.xiaomai.cloud.api.comm.aspect;

import com.google.common.util.concurrent.RateLimiter;
import com.xiaomai.cloud.api.comm.annotation.Limiter;
import com.xiaomai.cloud.api.comm.exception.LimitException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Madison
 * @date 2021/4/6
 */
@Aspect
@Component
public class RateLimiterAspect {
    //创建一个ConcurrentHashMap来存放各个方法和它们自己对应的RateLimiter对象
    private static final ConcurrentMap<String, RateLimiter> RATE_LIMITER = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.xiaomai.cloud.api.comm.annotation.Limiter)")
    public void rateLimit() {

    }

    private RateLimiter rateLimiter;

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        Object obj = null;
        //获取拦截的方法名
        Signature sig = point.getSignature();
        //获取拦截的方法名
        MethodSignature msig = (MethodSignature) sig;
        //返回被织入增加处理目标对象
        Object target = point.getTarget();
        //为了获取注解信息
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //获取注解信息
        Limiter annotation = currentMethod.getAnnotation(Limiter.class);
        double limitNum = annotation.LimitNum(); //获取注解每秒加入桶中的token
        String functionName = msig.getName(); // 注解所在方法名区分不同的限流策略

        if(RATE_LIMITER.containsKey(functionName)){
            rateLimiter=RATE_LIMITER.get(functionName);
        }else {
            RATE_LIMITER.put(functionName,RateLimiter.create(limitNum));
            rateLimiter=RATE_LIMITER.get(functionName);
        }
        if(rateLimiter.tryAcquire()) {
            return point.proceed();
        } else {
            throw new LimitException(500,"请求繁忙");
        }
    }
}