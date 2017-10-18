package com.hhly.smartdata.schedule;

import com.hhly.smartdata.service.schedule.IntervalExecutorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Iritchie.ren on 2017/9/25.
 */
@Component
public class IntervalExecutor{

    private final static Logger LOGGER = LoggerFactory.getLogger(IntervalExecutor.class);

    @Autowired
    private IntervalExecutorService intervalExecutorService;

    /**
     * 平台实时统计
     */
    @Scheduled(cron = "0 5/30 * * * ?")
    public void intervalSourceStatisticsSchedule(){
        try{
            intervalExecutorService.intervalSourceStatistics();
        }catch(Exception ex){
            LOGGER.error("定时器执行失败");
        }
    }

    /**
     * 接口统计
     */
    @Scheduled(cron = "0 5/30 * * * ?")
    public void intervalInterfaceStatisticsExecutor(){
        try{
            intervalExecutorService.intervalInterfaceStatistics();
        }catch(Exception ex){
            LOGGER.error("定时器执行失败");
        }
    }

    /**
     * 启动统计
     */
    @Scheduled(cron = "0 5/30 * * * ?")
    public void intervalGameLaunchSchedule(){
        try{
            intervalExecutorService.intervalGameLaunch();
        }catch(Exception ex){
            LOGGER.error("定时器执行失败");
        }
    }


}
