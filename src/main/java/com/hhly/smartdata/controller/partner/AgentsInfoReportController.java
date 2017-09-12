package com.hhly.smartdata.controller.partner;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 代理合集信息报表
 *
 * @author wanghuang
 */
@Controller
@RequestMapping(value = "/partner/agentsinforeport")
public class AgentsInfoReportController{
    private static Logger logger = Logger.getLogger(AgentsInfoReportController.class.getName());

    @RequestMapping(value = "/show")
    @RequiresPermissions(value = {"partner:cmsreport"})
    public ModelAndView show(String userId, ModelMap modelMap){
        modelMap.put("userId", userId);
        ModelAndView view = new ModelAndView();
        view.setViewName("operative/partner/agentsinforeport/show");
        return view;
    }


    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"partner:cmsreport"})
    @ResponseBody
    public String list(int pageNumber, int pageSize, String userId){
        String result = "";
        return result;
    }
}
