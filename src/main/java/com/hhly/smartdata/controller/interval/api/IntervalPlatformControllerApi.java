package com.hhly.smartdata.controller.interval.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.smartdata.IntervalSourceService;
import com.hhly.smartdata.util.HourListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

/**
 * 平台数据实时统计
 */
@RestController
@RequestMapping(value = "/interval/platform")
public class IntervalPlatformControllerApi extends BaseController {

    @Autowired
    private IntervalSourceService intervalSourceService;

    @RequestMapping(value = "/chart")
    public String getChart(String startDate, String endDate, String deviceTypes) {
        String result = "";
        try {
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result = intervalSourceService.selectIntervalSourceChartData(startDate, endDate, deviceTypes, scales).toString();
        } catch (Exception e) {
            LOGGER.error("获取平台实时曲线图数据异常");
        }
        return result;
    }

    @RequestMapping(value = "/list")
    public String search(String startDate, String endDate, int pageNumber, int pageSize) {
        String result = "";
        try {
            result = intervalSourceService.selectIntervalSourceListData(startDate, endDate, pageNumber, pageSize).toString();
        } catch (Exception e) {
            LOGGER.error("获取平台实时数据异常");
        }
        return result;
    }

    @RequestMapping(value = "/intervalNum")
    public String getIntervalNum(String startDate, String endDate) {
        String result = "";
        try {
            result = intervalSourceService.selectIntervalSourceToltalData(startDate, endDate).toString();
        } catch (Exception e) {
            LOGGER.error("获取平台实时各指标数量异常");
        }
        return result;
    }
}
