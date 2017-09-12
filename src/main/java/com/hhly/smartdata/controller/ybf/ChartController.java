package com.hhly.smartdata.controller.ybf;

import com.hhly.smartdata.service.ybf.DimInfoService;
import com.hhly.smartdata.service.ybf.DimInfoTypeService;
import com.hhly.smartdata.service.ybf.DimPositionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chart")
@RequiresPermissions(value = "forecast_manager")
public class ChartController{
    @Autowired
    DimPositionService locationService;
    @Autowired
    DimInfoTypeService dimInfoTypeService;
    @Autowired
    DimInfoService dimInfoService;

    @RequestMapping("list")
    @RequiresPermissions(value = "forecast_manager")
    public ModelAndView list(HttpSession session, ModelMap modelMap){
        modelMap.put("domains", locationService.getDomains());
        return new ModelAndView("operative/ybf/overview_history.main");
    }
}