package com.hhly.smartdata.controller.interval.api;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.service.smartdata.IntervalSourceService;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 各端实时数据
 */
@RestController
@RequestMapping(value = "/interval/source")
public class IntervalSourceControllerApi extends BaseController{

    @Autowired
    private IntervalSourceService intervalTerminalsSourceService;

    @RequestMapping(value = "/terminalsList")
    public Result getTerminalsList(String startDate, String endDate, int pageNumber, int pageSize){
        PageInfo<IntervalSourceReport> result = null;
        try{
            result = intervalTerminalsSourceService.selectIntervalTerminalsSourceListData(startDate, endDate, pageNumber, pageSize);
        }catch(Exception e){
            LOGGER.error("获取各端数据异常" + e.getMessage());
        }
        return Result.success(result);
    }

    @RequestMapping(value = "/list")
    public Result search(String startDate, String endDate, int pageNumber, int pageSize, String deviceType){
        PageInfo<IntervalSourceReport> result = null;
        try{
            result = intervalTerminalsSourceService.selectIntervalTimeTerminalsSourceListData(startDate, endDate, pageNumber, pageSize, deviceType);
        }catch(Exception e){
            LOGGER.error("获取分时段列表数据异常" + e.getMessage());
        }
        return Result.success(result);
    }

}
