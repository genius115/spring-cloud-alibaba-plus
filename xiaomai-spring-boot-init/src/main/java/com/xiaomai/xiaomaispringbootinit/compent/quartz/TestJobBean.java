package com.xiaomai.xiaomaispringbootinit.compent.quartz;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Madison
 * @date 2021/6/19
 */
@Service
public class TestJobBean extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        System.out.println("TestJobBean:::" + Thread.currentThread().getName() + ":::" + SimpleDateFormat.getDateTimeInstance().format(new Date()));
    }

}