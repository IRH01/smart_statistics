package com.hhly.smartdata.controller.interval;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.dto.enume.PlatformIdEnum;
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
 * 游戏启动统计
 */
@Controller
@RequestMapping(value = "/interval/IntervalGameLaunch")
public class IntervalGameLaunchController extends BaseController{

    @Autowired
    private IntervalGameLaunchService intervalGameLaunchService;


    @RequestMapping(value = "/show")
    public ModelAndView show(){
        Map<String, Object> model = Maps.newHashMap();
        model.put("platformTypes", PlatformIdEnum.values());
        return new ModelAndView("interval/game_launch", model);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public String getList(String startDate, String endDate, int pageNumber, int pageSize){
        String result = "";
        try{
            result = intervalGameLaunchService.selectIntervalGameLaunchListData(startDate, endDate, pageNumber, pageSize).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取平台各游戏启动列表数据异常");
        }
        return result;
    }

    @RequestMapping(value = "/chart")
    @ResponseBody
    public String getChart(String startDate, String endDate, String platformTypes){
        String result = "";
        try{
            TreeSet<String> scales = HourListUtil.getHourListPerHour();
            result = intervalGameLaunchService.selectIntervalGameLaunchChartData(startDate, endDate, platformTypes, scales).toString();
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error("获取平台各游戏启动曲线图数据异常");
        }
        return result;
    }

}
