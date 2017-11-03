package com.hhly.smartdata.schedule;

import com.hhly.smartdata.service.schedule.MonthExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */
@Component
public class MonthExecutor{

    private final static Logger LOGGER = LoggerFactory.getLogger(MonthExecutor.class);

    @Autowired
    private MonthExecutorService monthExecutorService;

    @Scheduled(cron = "* * 1 1 * MON-FRI")
    public void compositeReportExecutor(){
        Date date = new Date();
        try{
            this.monthExecutorService.compositeReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }

    @Scheduled(cron = "* * 1 1 * MON-FRI")
    public void registerExecutor(){
        Date date = new Date();
        try{
            this.monthExecutorService.registerReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }

    @Scheduled(cron = "* * 1 1 * MON-FRI")
    public void rechargeExecutor(){
        Date date = new Date();
        try{
            this.monthExecutorService.rechargeReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }

    @Scheduled(cron = "* * 1 1 * MON-FRI")
    public void loginSourceExecutor(){
        Date date = new Date();
        try{
            this.monthExecutorService.loginSourceReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }

}
