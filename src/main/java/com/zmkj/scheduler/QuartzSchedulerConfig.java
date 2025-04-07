package com.zmkj.scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

import java.io.IOException;
import java.util.Properties;

//@Configuration
//public class QuartzSchedulerConfig {
//    @Autowired
//    private DataSource mysqlDataSource;
//
//    @Bean
//    public SchedulerFactoryBean schedulerFactoryBean() {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        factory.setOverwriteExistingJobs(true);
//        factory.setDataSource(mysqlDataSource);
//        factory.setQuartzProperties(quartzProperties());
//        return factory;
//    }
//
//    @Bean
//    public JobDetail jobDetail() {
//        return JobBuilder.newJob(MyDistributedJob.class).withIdentity("myDistributeJob").storeDurably().build();
//    }
//
//
//    @Bean
//    public Trigger trigger(JobDetail jobDetail) {
//        return TriggerBuilder.newTrigger()
//                .forJob(jobDetail)
//                .withIdentity("myTrigger")
////                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
//                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
//                        .withIntervalInSeconds(5)
//                        .withRepeatCount(2))
//                .build();
//    }
//
//    @Bean
//    public Scheduler scheduler(SchedulerFactoryBean schedulerFactoryBean, Trigger trigger, JobDetail jobDetail) throws Exception {
//        Scheduler scheduler = schedulerFactoryBean.getScheduler();
//        scheduler.clear();
//        scheduler.scheduleJob(jobDetail, trigger);
//        return scheduler;
//    }
//
//
//
//    @Bean
//    public Properties quartzProperties() {
//        ClassPathResource resource = new ClassPathResource("/quartz.properties");
//        try {
//            return PropertiesLoaderUtils.loadProperties(resource);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to load quartz properties", e);
//        }
//    }
//
////    @Bean
////    public JobDetail sampleJobDetail() {
////        return newJob(MyDistributedJob.class)
////                .withIdentity("myJob", "group1")
////                .build();
////    }
////
////    @Bean
////    public Trigger sampleJobTrigger() {
////        return newTrigger()
////                .withIdentity("myTrigger", "group1")
////                .startNow()
////                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
////                        .withIntervalInSeconds(5)
////                        .withRepeatCount(10))
////                .build();
////    }
//}
