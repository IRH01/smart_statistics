package com.hhly.smartdata.controller.partner;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/partner/cmsreport")
public class CommissionsReportController{
    private static Logger logger = Logger.getLogger(CommissionsReportController.class.getName());

    @RequestMapping(value = "/show")
    @RequiresPermissions(value = {"partner:cmsreport"})
    public ModelAndView show(ModelMap modelMap){
        ModelAndView view = new ModelAndView();
        view.setViewName("operative/partner/commissions_report.main");
        return view;
    }


    @RequestMapping(value = "/getPtnCmsList", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"partner:cmsreport"})
    @ResponseBody
    public String getPtnCmsList(int pageNumber, int pageSize, String realName){
        String result = "";
        return result;
    }
}
