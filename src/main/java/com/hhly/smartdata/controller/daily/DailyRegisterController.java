package com.hhly.smartdata.controller.daily;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.PlatformIdEnum;
import com.hhly.smartdata.service.smartdata.DailyRegisterService;
import com.hhly.smartdata.service.smartdata.IntervalGameLaunchService;
import com.hhly.smartdata.util.HourListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.TreeSet;

/**
 *  注册来源统计
 */
@Controller
@RequestMapping(value="/daily/dailyRegister")
public class DailyRegisterController extends BaseController{

    @Autowired
    private DailyRegisterService dailyRegisterService;


    @RequestMapping(value="/show")
    public ModelAndView show(){
        Map<String, Object> model = Maps.newHashMap();
        model.put("platformTypes", PlatformIdEnum.values());
        return new ModelAndView("daily/register_statistics", model);
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public String getList(String startDate,String endDate,int pageNumber, int pageSize){
        String result ="";
        try{
            result  = dailyRegisterService.selectDailyRegisterListData(startDate,endDate,pageNumber,pageSize).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取注册来源统计异常");
        }
        return result;
    }

    @RequestMapping(value="/intervalNum")
    @ResponseBody
    public String getintervalNum(String startDate,String endDate){
        String result ="";
        try{
            result  = dailyRegisterService.selectYesterdayRegisterData(startDate,endDate).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取昨日新增注册用户数异常");
        }
        return result;
    }



}
