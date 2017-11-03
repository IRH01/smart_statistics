package com.hhly.smartdata.controller.schedule;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.schedule.MonthExecutorService;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Iritchie.ren on 2017/10/30.
 */
@RestController
@RequestMapping("/month/executor")
public class MonthExecutorControllerApi extends BaseController{

    @Autowired
    private MonthExecutorService monthExecutorService;

    @RequestMapping("composite/report")
    public Result compositeReportExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetMonthTime(date, 1);

        Result result = null;
        try{
            result = this.monthExecutorService.compositeReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("register")
    public Result registerExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetMonthTime(date, 1);

        Result result = null;
        try{
            result = this.monthExecutorService.registerReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("recharge")
    public Result rechargeExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetMonthTime(date, 1);

        Result result = null;
        try{
            result = this.monthExecutorService.rechargeReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }

    @RequestMapping("login/source")
    public Result loginSourceExecutor(String statisticsTime){
        Date date = DateUtil.string2Date(statisticsTime);
        if(date == null){
            date = new Date();
        }
        date = DateUtil.offsetMonthTime(date, 1);

        Result result = null;
        if(date == null){
            date = DateUtil.offsetMonthTime(new Date(), 1);
        }
        try{
            result = this.monthExecutorService.loginSourceReport(date);
        }catch(Exception e){
            LOGGER.error("定时器执行失败" + e.getMessage());
        }
        return result;
    }
}
