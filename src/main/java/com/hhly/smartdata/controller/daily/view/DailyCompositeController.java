package com.hhly.smartdata.controller.daily.view;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.source.SystemConfigServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 平台日综合报表
 */
@Controller
@RequestMapping(value = "/daily/composite")
public class DailyCompositeController extends BaseController{
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
        return new ModelAndView("daily/composite", model);
    }

}
