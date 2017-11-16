package com.hhly.smartdata.controller.schedule;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.schedule.IntervalExecutorService;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Iritchie.ren on 2017/10/30.
 */
@RestController
@Scope(value = "prototype")
@RequestMapping("/interval/executor")
public class IntervalExecutorControllerApi extends BaseController{

    private static final Integer INTERVAL_TIME = 30;
    @Autowired
    private IntervalExecutorService intervalExecutorService;

    /**
     * 平台实时统计
     */
    @RequestMapping("source/statistics")
    public Result intervalSourceStatisticsSchedule(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        if(date == null){
            date = new Date();
        }
        Date nowPointByThirtyMinute = DateUtil.getThirtyMinutePoint(date);
        String endDate = DateUtil.date2String(nowPointByThirtyMinute);
        Result result = null;
        try{
            result = intervalExecutorService.intervalSourceStatistics(endDate, INTERVAL_TIME);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }

    /**
     * 接口统计
     */
    @RequestMapping("interface/statistics")
    public Result intervalInterfaceStatisticsExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        if(date == null){
            date = new Date();
        }
        Date nowPointByThirtyMinute = DateUtil.getThirtyMinutePoint(date);
        String endDate = DateUtil.date2String(nowPointByThirtyMinute);
        Result result = null;
        try{
            result = intervalExecutorService.intervalInterfaceStatistics(endDate, INTERVAL_TIME);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }

    /**
     * 启动统计
     */
    @RequestMapping("game/launch")
    public Result intervalGameLaunchSchedule(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        if(date == null){
            date = new Date();
        }
        Date nowPointByThirtyMinute = DateUtil.getThirtyMinutePoint(date);
        String endDate = DateUtil.date2String(nowPointByThirtyMinute);
        Result result = null;
        try{
            result = intervalExecutorService.intervalGameLaunch(endDate, INTERVAL_TIME);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }
}
