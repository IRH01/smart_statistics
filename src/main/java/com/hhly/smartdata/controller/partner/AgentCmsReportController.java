package com.hhly.smartdata.controller.partner;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 代理佣金报表
 *
 * @author wanghuang
 */
@Controller
@RequestMapping(value = "/partner/agentcmsreport")
public class AgentCmsReportController{
    private static Logger logger = Logger.getLogger(AgentCmsReportController.class.getName());

    @RequestMapping(value = "/show")
    @RequiresPermissions(value = {"partner:cmsreport"})
    public ModelAndView show(String userId, String statMonth, ModelMap modelMap){
        modelMap.put("statMonth", statMonth);
        modelMap.put("userId", userId);

        ModelAndView view = new ModelAndView();
        view.setViewName("operative/partner/agentcmsreport/show");
        return view;
    }


    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"partner:cmsreport"})
    @ResponseBody
    public String list(int pageNumber, int pageSize, String userId, String statMonth){
        String result = "";
        return result;
    }
}
