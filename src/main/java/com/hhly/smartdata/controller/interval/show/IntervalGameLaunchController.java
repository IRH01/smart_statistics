package com.hhly.smartdata.controller.interval.show;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.source.SystemConfigServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 游戏启动统计
 */
@Controller
@RequestMapping(value = "/interval/game/launch")
public class IntervalGameLaunchController extends BaseController{

    @Autowired
    private SystemConfigServer systemConfigServer;

    @RequestMapping(value = "/show")
    public ModelAndView show(){
        Map<String, Object> model = Maps.newHashMap();
        Map<String, String> platformMap = null;
        try{
            platformMap = this.systemConfigServer.getPlatformMap();
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
        model.put("platformTypes", platformMap.values());
        return new ModelAndView("interval/game_launch");
    }

}
