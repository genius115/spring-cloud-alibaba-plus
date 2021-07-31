package com.xiaomai.xiaomaispringbootinit.compent.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Madison
 * @date 2021/6/19
 */
//@Configuration
public class CustomizeScheduleConfigTask {
    /**
     * 定时任务
     * @return
     */
    @Bean
    public JobDetail testJobDetail() {
        return JobBuilder.newJob(TestJobBean.class)
                .withIdentity("testJobDetail")
                .storeDurably()
                .build();
    }

    /**
     * 触发器，每间隔一段时间触发定时任务
     * @param jobDetail 具体执行的定时任务
     * @return
     */
    @Bean
    public Trigger testJobTrigger(@Qualifier("testJobDetail") JobDetail jobDetail) {
        ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                // 定时任务间隔时间
                .withIntervalInSeconds(3)
                // 触发器无限循环触发
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("testJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
