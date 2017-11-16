package com.hhly.smartdata.controller.interval.api;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.service.interval.IntervalCompositeService;
import com.hhly.smartdata.util.HourListUtil;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeSet;

/**
 * 平台数据实时统计
 */
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/interval/platform")
public class IntervalCompositeControllerApi extends BaseController{

    @Autowired
    private IntervalCompositeService intervalSourceService;

    @RequestMapping(value = "/list")
    public Result search(String startDate, String endDate, int pageNumber, int pageSize){
        PageInfo<IntervalSourceReport> result = null;
        try{
            result = intervalSourceService.selectIntervalSourceListData(startDate, endDate, pageNumber, pageSize);
        }catch(Exception e){
            LOGGER.error("获取平台实时数据异常" + e.getMessage());
        }
        return Result.success(result);
    }

    @RequestMapping(value = "/intervalNum")
    public Result getIntervalNum(String startDate, String endDate){
        Map<String, Object> result = null;
        try{
            result = intervalSourceService.selectIntervalSourceTotalData(startDate, endDate);
        }catch(Exception e){
            LOGGER.error("获取平台实时各指标数量异常" + e.getMessage());
        }
        return Result.success(result);
    }

    @RequestMapping(value = "/chart")
    public Result getChart(String startDate, String endDate, String sourceType){
        Map<String, Object> result = null;
        try{
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result = intervalSourceService.selectIntervalSourceChartData(startDate, endDate, sourceType, scales);
        }catch(Exception e){
            LOGGER.error("获取平台实时曲线图数据异常" + e.getMessage());
        }
        return Result.success(result);
    }
}
