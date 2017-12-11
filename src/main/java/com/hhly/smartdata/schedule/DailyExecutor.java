package com.hhly.smartdata.schedule;

import com.hhly.smartdata.service.schedule.DailyExecutorService;
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
public class DailyExecutor{

    private final static Logger LOGGER = LoggerFactory.getLogger(DailyExecutor.class);

    @Autowired
    private DailyExecutorService dailyExecutorService;

    /**
     * 平台综合报表
     */
    @Scheduled(cron = "* * 1 * * ?")
    public void dailyCompositeExecutor(){
        Date now = new Date();
        try{
            this.dailyExecutorService.compositeReport(now);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }

    /**
     * 注册统计
     */
    @Scheduled(cron = "* * 1 * * ?")
    public void dailyRegisterSchedule(){
        Date now = new Date();
        try{
            dailyExecutorService.registerStatistic(now);
        }catch(Exception ex){
            LOGGER.error("定时器执行失败" + ex.getMessage());
        }
    }

    /**
     * 充值报表
     */
    @Scheduled(cron = "* * 1 * * ?")
    public void rechargeExecutor(){
        Date now = new Date();
        try{
            dailyExecutorService.rechargeStatistic(now);
        }catch(Exception ex){
            LOGGER.error("定时器执行失败" + ex.getMessage());
        }
    }

    /**
     * 登录，用户来源报表
     */
    @Scheduled(cron = "* * 1 * * ?")
    public void loginSourceExecutor(){
        Date now = new Date();
        try{
            dailyExecutorService.loginStatistic(now);
        }catch(Exception ex){
            LOGGER.error("定时器执行失败" + ex.getMessage());
        }
    }

    /**
     * 留存分析
     */
    @Scheduled(cron = "* * 1 * * ?")
    public void keepRecordAnalyzeExecutor(){
        Date now = new Date();
        try{
            dailyExecutorService.keepRecordAnalyzeReport(now);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }
}
