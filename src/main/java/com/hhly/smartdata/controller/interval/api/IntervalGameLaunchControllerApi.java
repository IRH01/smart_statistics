package com.hhly.smartdata.controller.interval.api;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.smartdata.IntervalGameLaunchListReport;
import com.hhly.smartdata.service.smartdata.IntervalGameLaunchService;
import com.hhly.smartdata.util.HourListUtil;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeSet;

/**
 * 游戏启动统计
 */
@RestController
@RequestMapping(value = "/interval/game/launch")
public class IntervalGameLaunchControllerApi extends BaseController{

    @Autowired
    private IntervalGameLaunchService intervalGameLaunchService;

    @RequestMapping(value = "/list")
    public Result search(String startDate, String endDate, int pageNumber, int pageSize){
        PageInfo<IntervalGameLaunchListReport> result = null;
        try{
            result = intervalGameLaunchService.selectIntervalGameLaunchListData(startDate, endDate, pageNumber, pageSize);
        }catch(Exception e){
            LOGGER.error("获取平台各游戏启动列表数据异常" + e.getMessage());
        }
        return Result.success(result);
    }

    @RequestMapping(value = "/chart")
    public Result getChart(String startDate, String endDate){
        Map<String, Object> result = null;
        try{
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result = intervalGameLaunchService.selectIntervalGameLaunchChartData(startDate, endDate, scales);
        }catch(Exception e){
            LOGGER.error("获取平台各游戏启动曲线图数据异常" + e.getMessage());
        }
        return Result.success(result);
    }

}
