package com.hhly.smartdata.schedule;

import com.hhly.smartdata.service.schedule.IntervalExecutorService;
import com.hhly.smartdata.util.DateUtil;
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
public class IntervalExecutor{

    private final static Logger LOGGER = LoggerFactory.getLogger(IntervalExecutor.class);

    private static final Integer INTERVAL_TIME = 30;

    @Autowired
    private IntervalExecutorService intervalExecutorService;

    /**
     * 平台实时统计
     */
    @Scheduled(cron = "0 5/30 * * * ?")
    public void intervalSourceStatisticsSchedule(){
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getBeforeThirtyMinutePoint(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
        try{
            intervalExecutorService.intervalSourceStatistics(startDate, INTERVAL_TIME);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }

    /**
     * 接口统计
     */
    @Scheduled(cron = "0 5/30 * * * ?")
    public void intervalInterfaceStatisticsExecutor(){
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getBeforeThirtyMinutePoint(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
        try{
            intervalExecutorService.intervalInterfaceStatistics(startDate, INTERVAL_TIME);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }

    /**
     * 启动统计
     */
    @Scheduled(cron = "0 5/30 * * * ?")
    public void intervalGameLaunchSchedule(){
        Date now = new Date();
        Date nowPointByThirtyMinute = DateUtil.getBeforeThirtyMinutePoint(now);
        String startDate = DateUtil.date2String(nowPointByThirtyMinute);
        try{
            intervalExecutorService.intervalGameLaunch(startDate, INTERVAL_TIME);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
    }
}
