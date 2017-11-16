package com.hhly.smartdata.controller.interval.api;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.smartdata.IntervalInterfaceReport;
import com.hhly.smartdata.service.interval.IntervalInterfaceService;
import com.hhly.smartdata.util.HourListUtil;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeSet;

/**
 * 接口统计
 */
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/interval/interface")
public class IntervalInterfaceControllerApi extends BaseController{

    @Autowired
    private IntervalInterfaceService intervalInterfaceService;

    @RequestMapping(value = "/intervalNum")
    public Result getIntervalNum(String startDate, String endDate){
        PageInfo<IntervalInterfaceReport> result = null;
        try{
            result = intervalInterfaceService.selectIntervalInterfaceTotalData(startDate, endDate);
        }catch(Exception e){
            LOGGER.error("获取接口统计总数异常" + e.getMessage());
        }
        return Result.success(result);
    }

    @RequestMapping(value = "/chart")
    public Result getChart(String startDate, String endDate, String interfaceCode){
        Map<String, Object> result = null;
        try{
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result = intervalInterfaceService.selectIntervalInterfaceChartData(startDate, endDate, interfaceCode, scales);
        }catch(Exception e){
            LOGGER.error("获取平台实时曲线图数据异常" + e.getMessage());
        }
        return Result.success(result);
    }

}
