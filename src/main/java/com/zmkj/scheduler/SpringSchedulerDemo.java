package com.zmkj.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

//@Configuration
public class SpringSchedulerDemo {

    @Scheduled(cron = "0/3 * * * * ?")
    public void pint() {
        System.out.println("time :" + new Date());
    }
}
