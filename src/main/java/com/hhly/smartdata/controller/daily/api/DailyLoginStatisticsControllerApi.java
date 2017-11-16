package com.hhly.smartdata.controller.daily.api;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.daily.DailyLoginStatisticsService;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@RestController
@Scope(value = "prototype")
@RequestMapping("/daily/login/statistics")
public class DailyLoginStatisticsControllerApi extends BaseController{

    @Autowired
    private DailyLoginStatisticsService dailyLoginStatisticsService;

    @RequestMapping("list")
    public Result search(String monthOfYear){
        Map<String, List> searchMap = Maps.newHashMap();
        try{
            searchMap = this.dailyLoginStatisticsService.search(monthOfYear);
        }catch(Exception e){
            LOGGER.error("查询月登录报表报错：" + e.getMessage());
        }
        return Result.success(searchMap);
    }

    @RequestMapping("last/total")
    public Result getLastTotalRegister(){
        Map<String, Object> lastTotalRegisterMap = null;
        try{
            lastTotalRegisterMap = this.dailyLoginStatisticsService.getLastTotalRegister();
        }catch(Exception e){
            LOGGER.error("统计月登录报表报错：" + e.getMessage());
        }
        return Result.success(lastTotalRegisterMap);
    }


}
