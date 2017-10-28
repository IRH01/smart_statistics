package com.hhly.smartdata.controller.daily.api;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.daily.DailyCompositeReportResult;
import com.hhly.smartdata.service.smartdata.DailyCompositeService;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台日综合报表
 */
@RestController
@RequestMapping(value = "/daily/composite")
public class DailyCompositeControllerApi extends BaseController{

    @Autowired
    private DailyCompositeService dailyCompositeService;

    @RequestMapping(value = "/list")
    public Result search(String startDate, String endDate, int pageNumber, int pageSize){
        PageInfo<DailyCompositeReportResult> result = null;
        try{
            result = dailyCompositeService.selectDailyCompositeListData(startDate, endDate, pageNumber, pageSize);
        }catch(Exception e){
            LOGGER.error("获取平台日综合报表数据异常" + e.getMessage());
        }
        return Result.success(result);
    }
}
