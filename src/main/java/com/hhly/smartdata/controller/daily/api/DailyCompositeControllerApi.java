package com.hhly.smartdata.controller.daily.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.smartdata.DailyCompositeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台日综合报表
 */
@RestController
@RequestMapping(value = "/daily/composite")
public class DailyCompositeControllerApi extends BaseController {

    @Autowired
    private DailyCompositeService dailyCompositeService;

    @RequestMapping(value = "/list")
    public String search(String startDate, String endDate, int pageNumber, int pageSize) {
        String result = "";
        try {
            result = dailyCompositeService.selectDailyCompositeListData(startDate, endDate, pageNumber, pageSize).toString();
        } catch (Exception e) {
            LOGGER.error("获取平台日综合报表数据异常");
        }
        return result;
    }


}
