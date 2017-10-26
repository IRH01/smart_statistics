package com.hhly.smartdata.controller.interval.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.smartdata.IntervalTerminalsSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 各端实时数据
 */
@RestController
@RequestMapping(value = "/interval/source")
public class IntervalTerminalsSourceControllerApi extends BaseController {

    @Autowired
    private IntervalTerminalsSourceService intervalTerminalsSourceService;

    @RequestMapping(value = "/terminalsList")
    public String getTerminalsList(String startDate, String endDate, int pageNumber, int pageSize) {
        String result = "";
        try {
            result = intervalTerminalsSourceService.selectIntervalTerminalsSourceListData(startDate, endDate, pageNumber, pageSize).toString();
        } catch (Exception e) {
            LOGGER.error("获取各端数据异常");
        }
        return result;
    }

    @RequestMapping(value = "/list")
    public String search(String startDate, String endDate, int pageNumber, int pageSize, String deviceType) {
        String result = "";
        try {
            result = intervalTerminalsSourceService.selectIntervalTimeTerminalsSourceListData(startDate, endDate, pageNumber, pageSize, deviceType).toString();
        } catch (Exception e) {
            LOGGER.error("获取分时段列表数据异常");
        }
        return result;
    }

}
