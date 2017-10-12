package com.hhly.smartdata.controller.interval;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.service.smartdata.IntervalSourceService;
import com.hhly.smartdata.util.HourListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import java.util.TreeSet;

/**
 *  平台数据实时统计
 */
@Controller
@RequestMapping(value="/interval/realTimeInterval")
public class IntervalSourceController extends BaseController{

    @Autowired
    private IntervalSourceService intervalSourceService;


    @RequestMapping(value="/show")
    public ModelAndView show(){
        Map<String, Object> model = Maps.newHashMap();
        model.put("deviceTypes", SourceTypeEnum.values());
        return new ModelAndView("interval/source_statistics", model);
    }

    @RequestMapping(value="/chart")
    @ResponseBody
    public String getChart(String startDate,String endDate,String deviceTypes) {
        String result ="";
        try{
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result =  intervalSourceService.selectIntervalSourceChartData(startDate,endDate,deviceTypes,scales).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取平台实时曲线图数据异常");
        }
        return result;
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public String getList(String startDate,String endDate,int pageNumber, int pageSize){
        String result ="";
        try{
            result =  intervalSourceService.selectIntervalSourceListData(startDate,endDate,pageNumber,pageSize).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取平台实时数据异常");
        }
        return result;
    }

    @RequestMapping(value="/intervalNum")
    @ResponseBody
    public String getintervalNum(String startDate,String endDate){
        String result ="";
        try{
            result  = intervalSourceService.selectIntervalSourceToltalData(startDate,endDate).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取平台实时各指标数量异常");
        }
        return result;
    }
}
