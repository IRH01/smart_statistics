package com.hhly.smartdata.controller.daily.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.smartdata.DailyRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册来源统计
 */
@RestController
@RequestMapping(value = "/daily/register")
public class DailyRegisterControllerApi extends BaseController {

    @Autowired
    private DailyRegisterService dailyRegisterService;

    @RequestMapping(value = "/list")
    public String search(String startDate, String endDate, int pageNumber, int pageSize) {
        String result = "";
        try {
            result = dailyRegisterService.selectDailyRegisterListData(startDate, endDate, pageNumber, pageSize).toString();
        } catch (Exception e) {
            LOGGER.error("获取注册来源统计异常");
        }
        return result;
    }

    @RequestMapping(value = "/intervalNum")
    public String getIntervalNum(String startDate, String endDate) {
        String result = "";
        try {
            result = dailyRegisterService.selectYesterdayRegisterData(startDate, endDate).toString();
        } catch (Exception e) {
            LOGGER.error("获取昨日新增注册用户数异常");
        }
        return result;
    }

}
