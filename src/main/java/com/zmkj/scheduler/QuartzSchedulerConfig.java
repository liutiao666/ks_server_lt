package com.zmkj.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzSchedulerConfig {
    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(MyDistributedJob.class).withIdentity("myDistributeJob").storeDurably().build();
    }


    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("myTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/10 * * * * ?"))
                .build();
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail jobDetail) throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(jobDetail, trigger);
        return scheduler;
    }
}
