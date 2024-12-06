package com.zmkj.config;

import com.zmkj.scheduler.MyDistributedJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PostConstruct;

//@Configuration
//@DependsOn("mysqlDataSource")
public class JobInit {

    @Autowired
    public Scheduler scheduler;

    @PostConstruct
    public void initJob() throws SchedulerException {
        SchedulerMetaData metaData = scheduler.getMetaData();
        System.out.println(metaData);
        JobDetail jobDetail = JobBuilder.newJob(MyDistributedJob.class).storeDurably(true).withIdentity("myDistributedJob").build();
        SimpleTrigger trigger = TriggerBuilder.newTrigger().startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(10)).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
