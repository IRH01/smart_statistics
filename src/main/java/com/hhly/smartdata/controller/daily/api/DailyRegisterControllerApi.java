package com.hhly.smartdata.controller.daily.api;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.smartdata.DailyRegisterReport;
import com.hhly.smartdata.service.smartdata.DailyRegisterService;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册来源统计
 */
@RestController
@RequestMapping(value = "/daily/register")
public class DailyRegisterControllerApi extends BaseController{

    @Autowired
    private DailyRegisterService dailyRegisterService;

    @RequestMapping(value = "/list")
    public Result search(String startDate, String endDate, int pageNumber, int pageSize){
        PageInfo<DailyRegisterReport> result = null;
        try{
            result = dailyRegisterService.selectDailyRegisterListData(startDate, endDate, pageNumber, pageSize);
        }catch(Exception e){
            LOGGER.error("获取注册来源统计异常" + e.getMessage());
        }
        return Result.success(result);
    }

    @RequestMapping(value = "/intervalNum")
    public Result getIntervalNum(String startDate, String endDate){
        DailyRegisterReport result = null;
        try{
            result = dailyRegisterService.selectYesterdayRegisterData(startDate, endDate);
        }catch(Exception e){
            LOGGER.error("获取昨日新增注册用户数异常" + e.getMessage());
        }
        return Result.success(result);
    }

}
