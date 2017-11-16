package com.hhly.smartdata.controller.schedule;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.schedule.DailyExecutorService;
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
@RequestMapping("/daily/executor")
public class DailyExecutorControllerApi extends BaseController{

    @Autowired
    private DailyExecutorService dailyExecutorService;

    /**
     * 平台综合报表###################
     */
    @RequestMapping("composite")
    public Result dailyCompositeExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        Result result = null;
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetDayTime(date, 1);
        try{
            result = this.dailyExecutorService.compositeReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }

    /**
     * 注册统计.用户来源统计
     */
    @RequestMapping("register")
    public Result dailyRegisterSchedule(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        Result result = null;
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetDayTime(date, 1);

        try{
            result = dailyExecutorService.registerStatistic(date);
        }catch(Exception ex){
            LOGGER.error("定时器执行失败" + ex.getMessage());
        }
        return result;
    }

    /**
     * 充值报表
     */
    @RequestMapping("recharge")
    public Result rechargeExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        Result result = null;
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetDayTime(date, 1);

        try{
            result = dailyExecutorService.rechargeStatistic(date);
        }catch(Exception ex){
            LOGGER.error("定时器执行失败" + ex.getMessage());
        }
        return result;
    }

    /**
     * 登录来源报表
     */
    @RequestMapping("login/source")
    public Result loginSourceExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        Result result = null;
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetDayTime(date, 1);

        try{
            result = dailyExecutorService.loginStatistic(date);
        }catch(Exception ex){
            LOGGER.error("定时器执行失败" + ex.getMessage());
        }
        return result;
    }

    /**
     * 留存分析
     */
    @RequestMapping("keep/record/analyze")
    public Result keepRecordAnalyzeExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        Result result = null;
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetDayTime(date, 1);

        try{
            result = dailyExecutorService.keepRecordAnalyzeReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }
}
