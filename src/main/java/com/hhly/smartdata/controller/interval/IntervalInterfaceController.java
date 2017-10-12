package com.hhly.smartdata.controller.interval;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.service.smartdata.IntervalInterfaceService;
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
 *  接口统计
 */
@Controller
@RequestMapping(value="/interval/IntervalInterface")
public class IntervalInterfaceController extends BaseController{

    @Autowired
    private IntervalInterfaceService intervalInterfaceService;


    @RequestMapping(value="/show")
    public ModelAndView show(){
        Map<String, Object> model = Maps.newHashMap();
        model.put("deviceTypes", SourceTypeEnum.values());
        return new ModelAndView("interval/interface_statistics", model);
    }

    @RequestMapping(value="/intervalNum")
    @ResponseBody
    public String getintervalNum(String startDate,String endDate){
        String result ="";
        try{
            result  = intervalInterfaceService.selectIntervalInterfaceToltalData(startDate,endDate).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取接口统计总数异常");
        }
        return result;
    }

    @RequestMapping(value="/chart")
    @ResponseBody
    public String getChart(String startDate,String endDate,String interfaceCode) {
        String result ="";
        try{
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result =  intervalInterfaceService.selectIntervalInterfaceChartData(startDate,endDate,interfaceCode,scales).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取平台实时曲线图数据异常");
        }
        return result;
    }

}
