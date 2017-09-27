package com.hhly.smartdata.schedule;

import com.hhly.smartdata.service.schedule.MonthExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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
        try{
            this.monthExecutorService.compositeReport();
        }catch(Exception ex){
            LOGGER.error("定时器执行失败");
        }
    }

    @Scheduled(cron = "* * 1 1 * MON-FRI")
    public void registerExecutor(){
        try{
            this.monthExecutorService.registerReport();
        }catch(Exception e){
            LOGGER.error("定时器执行失败");
        }
    }

    @Scheduled(cron = "* * 1 1 * MON-FRI")
    public void rechargeExecutor(){
        try{
            this.monthExecutorService.rechargeReport();
        }catch(Exception e){
            LOGGER.error("定时器执行失败");
        }
    }

    @Scheduled(cron = "* * 1 1 * MON-FRI")
    public void loginSourceExecutor(){
        try{
            this.monthExecutorService.loginSourceReport();
        }catch(Exception e){
            LOGGER.error("定时器执行失败");
        }
    }

}
