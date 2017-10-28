package com.hhly.smartdata.controller.daily.api;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.daily.DailyKeepRecordReportResult;
import com.hhly.smartdata.service.smartdata.DailyKeepRecordService;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 留存分析
 */
@RestController
@RequestMapping(value = "/daily/keep/record")
public class DailyKeepRecordControllerApi extends BaseController{

    @Autowired
    private DailyKeepRecordService dailyKeepRecordService;

    @RequestMapping(value = "/list")
    public Result search(String startDate, String endDate, int pageNumber, int pageSize){
        PageInfo<DailyKeepRecordReportResult> result = null;
        try{
            result = dailyKeepRecordService.selectDailyKeepRecordListData(startDate, endDate, pageNumber, pageSize);
        }catch(Exception e){
            LOGGER.error("获取留存分析报表数据异常" + e.getMessage());
        }
        return Result.success(result);
    }


}
