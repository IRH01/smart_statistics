package com.hhly.smartdata.controller.partner;

import com.hhly.smartdata.service.partner.PartnerRefWebsiteSumService;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 代理推广链接来源报表
 *
 * @author wanghuang
 */
@Controller
@RequestMapping(value = "/partner/ptnsitesum")
public class PartnerRefWebsiteSumController{

    @Autowired
    private PartnerRefWebsiteSumService partnerRefWebsiteSumService;

    @RequestMapping(value = "/show")
    @RequiresPermissions(value = {"partner:ptnsitesum"})
    public ModelAndView show(ModelMap modelMap){
        ModelAndView view = new ModelAndView();
        view.setViewName("operative/partner/ref_website_sum.main");
        return view;
    }

    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"partner:ptnsitesum"})
    @ResponseBody
    public String list(int pageNumber, int pageSize, HttpServletRequest request){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        conditionMap.put("userId", request.getParameter("userId"));
        conditionMap.put("name", request.getParameter("name"));
        conditionMap.put("order", request.getParameter("order"));
        conditionMap.put("orderField", request.getParameter("orderField"));
        return JSONObject.fromObject(partnerRefWebsiteSumService.find(conditionMap, pageNumber, pageSize)).toString();
    }


}
