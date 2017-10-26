package com.hhly.smartdata.controller.daily.api;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.PlatformIdEnum;
import com.hhly.smartdata.service.smartdata.DailyKeepRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 *  留存分析
 */
@RestController
@RequestMapping(value="/daily/keep/record")
public class DailyKeepRecordControllerApi extends BaseController{

    @Autowired
    private DailyKeepRecordService dailyKeepRecordService;

    @RequestMapping(value="/list")
    public String search(String startDate, String endDate, int pageNumber, int pageSize){
        String result ="";
        try{
            result  = dailyKeepRecordService.selectDailyKeepRecordListData(startDate,endDate,pageNumber,pageSize).toString();
        }catch(Exception e){
            LOGGER.error("获取留存分析报表数据异常");
        }
        return result;
    }


}
