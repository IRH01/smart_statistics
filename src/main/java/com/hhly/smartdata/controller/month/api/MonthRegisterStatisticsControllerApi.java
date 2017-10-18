package com.hhly.smartdata.controller.month.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.share.TimeFilter;
import com.hhly.smartdata.service.month.MonthRegisterStatisticsService;
import com.hhly.smartdata.util.Result;
import com.hhly.smartdata.util.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@RestController
@RequestMapping("/month/register/statistics/")
public class MonthRegisterStatisticsControllerApi extends BaseController{

    @Autowired
    private MonthRegisterStatisticsService monthRegisterStatisticsService;

    @RequestMapping("list")
    public Result search(TimeFilter filter){
        Pagination pagination = null;
        try{
            pagination = this.monthRegisterStatisticsService.search(filter);
        }catch(Exception e){
            LOGGER.error("查询月综合报表报错：" + e.getMessage());
        }
        return Result.success(pagination);
    }

    @RequestMapping("last/register/total")
    public Result getLastTotalRegister(){
        long total = 0;
        try{
            total = this.monthRegisterStatisticsService.getLastTotalRegister();
        }catch(Exception e){
            LOGGER.error("获取最近一个月注册人数报错：" + e.getMessage());
        }
        return Result.success(total);
    }
}
