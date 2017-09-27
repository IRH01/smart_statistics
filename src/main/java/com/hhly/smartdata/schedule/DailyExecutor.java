package com.hhly.smartdata.schedule;

import com.hhly.smartdata.service.schedule.DailyExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */
@Component
public class DailyExecutor{

    private final static Logger LOGGER = LoggerFactory.getLogger(DailyExecutor.class);

    @Autowired
    private DailyExecutorService dailyExecutorService;

    /**
     * 平台综合报表
     */
    @Scheduled(cron = "* * 1 * * MON-FRI")
    public void dailyCompositeExecutor(){
        try{
            this.dailyExecutorService.compositeReport();
        }catch(Exception e){
            LOGGER.error("定时器执行失败");
        }
    }

    /**
     * 注册统计
     */
    @Scheduled(cron = "* * 1 * * MON-FRI")
    public void dailyRegisterSchedule(){
        try{
            dailyExecutorService.registerStatistic();
        }catch(Exception ex){
        }
    }

    /**
     * 充值报表
     */
    @Scheduled(cron = "* * 1 * * MON-FRI")
    public void rechargeExecutor(){
        try{
            dailyExecutorService.rechargeStatistic();
        }catch(Exception ex){
            LOGGER.error("定时器执行失败");
        }
    }

    /**
     * 登录，用户来源报表
     */
    @Scheduled(cron = "* * 1 * * MON-FRI")
    public void loginSourceExecutor(){
        try{
            dailyExecutorService.loginStatistic();
        }catch(Exception ex){
            LOGGER.error("定时器执行失败");
        }
    }

    /**
     * 留存分析
     */
    @Scheduled(cron = "* * 1 * * MON-FRI")
    public void keepRecordAnalyzeExecutor(){
        try{
            this.dailyExecutorService.keepRecordAnalyzeReport();
        }catch(Exception e){
            LOGGER.error("定时器执行失败");
        }
    }
}
