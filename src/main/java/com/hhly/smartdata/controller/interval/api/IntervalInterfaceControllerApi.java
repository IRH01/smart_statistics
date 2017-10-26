package com.hhly.smartdata.controller.interval.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.smartdata.IntervalInterfaceService;
import com.hhly.smartdata.util.HourListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

/**
 * 接口统计
 */
@RestController
@RequestMapping(value = "/interval/interface")
public class IntervalInterfaceControllerApi extends BaseController {

    @Autowired
    private IntervalInterfaceService intervalInterfaceService;

    @RequestMapping(value = "/intervalNum")
    public String getIntervalNum(String startDate, String endDate) {
        String result = "";
        try {
            result = intervalInterfaceService.selectIntervalInterfaceToltalData(startDate, endDate).toString();
        } catch (Exception e) {
            LOGGER.error("获取接口统计总数异常");
        }
        return result;
    }

    @RequestMapping(value = "/chart")
    public String getChart(String startDate, String endDate, String interfaceCode) {
        String result = "";
        try {
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result = intervalInterfaceService.selectIntervalInterfaceChartData(startDate, endDate, interfaceCode, scales).toString();
        } catch (Exception e) {
            LOGGER.error("获取平台实时曲线图数据异常");
        }
        return result;
    }

}
