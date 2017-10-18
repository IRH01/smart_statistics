package com.hhly.smartdata.controller.month.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.service.month.MonthLoginStatisticsService;
import com.hhly.smartdata.util.Result;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@RestController
@RequestMapping("/month/login/statistics")
public class MonthLoginStatisticsControllerApi extends BaseController{

    @Autowired
    private MonthLoginStatisticsService monthLoginStatisticsService;

    @RequestMapping("list")
    public Result search(TimeFilter filter){
        Pagination pagination = null;
        try{
            pagination = this.monthLoginStatisticsService.search(filter);
        }catch(Exception e){
            LOGGER.error("查询月登录报表报错：" + e.getMessage());
        }
        return Result.success(pagination);
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
