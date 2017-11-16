package com.hhly.smartdata.controller.interval.api;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.service.interval.IntervalSourceService;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 各端实时数据
 */
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/interval/source")
public class IntervalSourceControllerApi extends BaseController{

    @Autowired
    private IntervalSourceService intervalTerminalsSourceService;

    @RequestMapping(value = "/terminalsList")
    public Result getTerminalsList(String startDate, String endDate){
        List<IntervalSourceReport> result = null;
        try{
            result = intervalTerminalsSourceService.selectTotalDaySourceListData(startDate, endDate);
        }catch(Exception e){
            LOGGER.error("获取各端数据异常" + e.getMessage());
        }
        return Result.success(result);
    }

    @RequestMapping(value = "/list")
    public Result search(String startDate, String endDate, int pageNumber, int pageSize, String sourceType){
        PageInfo<IntervalSourceReport> result = null;
        try{
            result = intervalTerminalsSourceService.selectIntervalTimeTerminalsSourceListData(startDate, endDate, pageNumber, pageSize, sourceType);
        }catch(Exception e){
            LOGGER.error("获取分时段列表数据异常" + e.getMessage());
        }
        return Result.success(result);
    }

}
