package com.xiaomai.xiaomaispringbootinit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * retry重试机制  aspect  注意切面的使用  同类方法调用问题
 *
 *
 * @Retryable注解中的参数说明：
 * maxAttempts :最大重试次数，默认为3，如果要设置的重试次数为3，可以不写；
 * value：抛出指定异常才会重试
 * include：和value一样，默认为空，当exclude也为空时，所有异常都重试
 * exclude：指定不处理的异常，默认空，当include也为空时，所有异常都重试
 * backoff：重试等待策略，默认使用@Backoff@Backoff的value默认为1000L，我们设置为2000L。
 *
 *
 * @Backoff注解中的参数说明：
 * value：隔多少毫秒后重试，默认为1000L，我们设置为3000L；
 * delay：和value一样，但是默认为0；
 * multiplier（指定延迟倍数）默认为0，表示固定暂停1秒后进行重试，如果把multiplier设置为1.5，则第一次重试为2秒，第二次为3秒，第三次为4.5秒。
 *
 *
 * 1、由于retry用到了aspect增强，所有会有aspect的坑，就是方法内部调用，会使aspect增强失效，那么retry当然也会失效。参考改链接
 * 2、重试机制，不能在接口实现类里面写。所以要做重试，必须单独写个service。
 *
 *
 * @author Madison
 * @date 2021/5/13
 */
@Service
public class RetryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryService.class);

    private AtomicInteger count = new AtomicInteger(1);

    @Retryable(value = { RemoteAccessException.class }, maxAttemptsExpression = "${retry.maxAttempts:10}",
            backoff = @Backoff(delayExpression = "${retry.backoff:1000}"))
    public void retry() {
        LOGGER.info("start to retry : " + count.getAndIncrement());
        throw new RemoteAccessException("here " + count.get());
    }

    //在指定方法上标记@Recover来开启重试失败后调用的方法(注意,需跟重处理方法在同一个类中)

    @Recover
    public void recover(RemoteAccessException t) {
        LOGGER.info("SampleRetryService.recover:{}", t.getClass().getName());
    }

    /***
    @Retryable(value = RemoteAccessException.class)
    public void service1(String str1, String str2) {
        // ... do something
        LOGGER.info("do something : " + "service1");
        throw new RemoteAccessException("service1 " + count.get());
    }

    @Retryable(value = RemoteAccessException.class)
    public void service2(String str1, String str2) {
        // ... do something
        LOGGER.info("do something : " + "service1");
        throw new RemoteAccessException("service2 " + count.get());
    }

    @Recover
    public void service1Recover(RemoteAccessException e, String str1, String str2) {
        // ... error handling making use of original args if required
        LOGGER.info("service1Recover : " + new Date());
    }

    @Recover
    public void service2Recover(RemoteAccessException e, String str1, String str2) {
        // ... error handling making use of original args if required
        LOGGER.info("service2Recover : " + new Date());
    }
    */
}
