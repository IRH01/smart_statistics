package com.hhly.smartdata.controller.month.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.month.MonthLoginStatisticsService;
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
@RequestMapping("/month/login/statistics")
public class MonthLoginStatisticsControllerApi extends BaseController{

    @Autowired
    private MonthLoginStatisticsService monthLoginStatisticsService;

    @RequestMapping("list")
    public Result search(String year){
        Map<String, List> map = null;
        try{
            map = this.monthLoginStatisticsService.search(year);
        }catch(Exception e){
            LOGGER.error("查询月登录报表报错：" + e.getMessage());
        }
        return Result.success(map);
    }

    @RequestMapping("last/total")
    public Result getLastTotalRegister(){
        Map<String, Object> lastTotalRegisterMap = null;
        try{
            lastTotalRegisterMap = this.monthLoginStatisticsService.getLastTotalRegister();
        }catch(Exception e){
            LOGGER.error("统计月登录报表报错：" + e.getMessage());
        }
        return Result.success(lastTotalRegisterMap);
    }

}
