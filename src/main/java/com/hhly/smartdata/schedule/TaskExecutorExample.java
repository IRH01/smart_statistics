package com.hhly.smartdata.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskExecutorExample{

    @Scheduled(cron = "*/5 * * * * MON-FRI")
    public void doSomething(){
        System.err.println("定时任务测试");
    }
}