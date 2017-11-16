package com.hhly.smartdata.controller.interval.api;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.interval.IntervalGameLaunchService;
import com.hhly.smartdata.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 游戏启动统计
 */
@RestController
@Scope(value = "prototype")
@RequestMapping(value = "/interval/game/launch")
public class IntervalGameLaunchControllerApi extends BaseController{

    @Autowired
    private IntervalGameLaunchService intervalGameLaunchService;

    /**
     * 获取平台各游戏启动列表数据
     *
     * @return
     */
    @RequestMapping(value = "/list")
    public Map<String, Object> list(String sourceType){
        Map<String, Object> result = Maps.newHashMap();
        try{
            result = intervalGameLaunchService.selectIntervalGameLaunchTimeListData(sourceType);
        }catch(Exception e){
            LOGGER.error("获取平台各游戏启动列表数据异常:" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取游戏启动次数图形数据
     *
     * @return
     */
    @RequestMapping(value = "/chart")
    public Result chart(String sourceType){
        Map<String, Object> result = null;
        try{
            result = intervalGameLaunchService.selectIntervalGameLaunchChartTimeData(sourceType);
        }catch(Exception e){
            LOGGER.error("获取平台各游戏启动曲线图数据异常" + e.getMessage());
        }
        return Result.success(result);
    }
}
