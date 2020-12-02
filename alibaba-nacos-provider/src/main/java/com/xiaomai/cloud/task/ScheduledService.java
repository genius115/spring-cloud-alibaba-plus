package com.xiaomai.cloud.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * 使用的注解： @Scheduled 和 @EnableScheduling
 * 因为是串行执行，会有阻塞问题。解决方案：1 任务提交到一个自定义线程池  2 @Async   3 使用并行方式
 * 定时任务为单线程串行执行,存在阻塞问题
 *
 * 并行方式
 * 当定时任务很多的时候，为了提高任务执行效率，可以采用并行方式执行定时任务，任务之间互不影响，
 * 只要实现SchedulingConfigurer接口就可以。
 *
 @Scheduled
 https://www.jianshu.com/p/1defb0f22ed1
 1/cron *****
 @Scheduled(cron="${time.cron}")
 @Scheduled(cron="0/${time.interval} * * * * *")
 2/fixedRate  *****
 上一次开始执行时间点之后多长时间再执行。@Scheduled(fixedRate = 5000) //上一次开始执行时间点之后5秒再执行
 3/fixedRateString
 与 fixedRate 意思相同，只是使用字符串的形式。唯一不同的是支持占位符。
 4/initialDelay  *****
 第一次延迟多长时间后再执行。如：@Scheduled(initialDelay=1000, fixedRate=5000) //第一次延迟1秒后执行，之后按fixedRate的规则每5秒执行一次
 5/ zone
 时区，接收一个java.util.TimeZone#ID。cron表达式会基于该时区解析。默认是一个空字符串，即取服务器所在地的时区。比如我们一般使用的时区Asia/Shanghai。该字段我们一般留空。
 6/fixedDelay *****
 上一次执行完毕时间点之后多长时间再执行。如： @Scheduled(fixedDelay = 5000) //上一次执行完毕时间点之后5秒再执行
 7、fixedDelayString
 与 fixedDelay 意思相同，只是使用字符串的形式。唯一不同的是支持占位符。如： @Scheduled(fixedDelayString = "5000") //上一次执行完毕时间点之后5秒再执行
 占位符的使用(配置文件中有配置：time.fixedDelay=5000)： @Scheduled(fixedDelayString = "${time.fixedDelay}")
 *
 * @author wangfeng
 * @date 2020/11/26
 */
@Slf4j
//@Component
public class ScheduledService {

    //@Async
    //@Scheduled(cron = "0/5 * * * * *")
    public void scheduled(){
        log.info("=====>>>>>使用cron  {}",System.currentTimeMillis());
    }

    //@Async
    //@Scheduled(fixedRate = 5000)
    public void scheduled1() {
        log.info("=====>>>>>使用fixedRate{}", System.currentTimeMillis());
    }

    //@Async
    //@Scheduled(fixedDelay = 5000)
    public void scheduled2() {
        log.info("=====>>>>>fixedDelay{}",System.currentTimeMillis());
    }
}
