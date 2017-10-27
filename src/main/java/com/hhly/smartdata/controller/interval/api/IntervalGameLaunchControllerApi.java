package com.hhly.smartdata.controller.interval.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.smartdata.IntervalGameLaunchService;
import com.hhly.smartdata.util.HourListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.TreeSet;

/**
 * 游戏启动统计
 */
@RestController
@RequestMapping(value = "/interval/game/launch")
public class IntervalGameLaunchControllerApi extends BaseController {

    @Autowired
    private IntervalGameLaunchService intervalGameLaunchService;

    @RequestMapping(value = "/list")
    public String search(String startDate, String endDate, int pageNumber, int pageSize) {
        String result = "";
        try {
            result = intervalGameLaunchService.selectIntervalGameLaunchListData(startDate, endDate, pageNumber, pageSize).toString();
        } catch (Exception e) {
            LOGGER.error("获取平台各游戏启动列表数据异常");
        }
        return result;
    }

    @RequestMapping(value = "/chart")
    public String getChart(String startDate, String endDate) {
        String result = "";
        try {
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result = intervalGameLaunchService.selectIntervalGameLaunchChartData(startDate, endDate, scales).toString();
        } catch (Exception e) {
            LOGGER.error("获取平台各游戏启动曲线图数据异常");
        }
        return result;
    }

}
