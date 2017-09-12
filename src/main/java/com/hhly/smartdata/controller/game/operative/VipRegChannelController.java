package com.hhly.smartdata.controller.game.operative;

import com.hhly.smartdata.service.game.operative.PartnerMemberConService;
import com.hhly.smartdata.service.game.operative.VipRegChannelStatDetailService;
import com.hhly.smartdata.service.game.operative.VipRegChannelStatSumService;
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

@Controller
@RequestMapping(value = "/game/operative/vipregchannel")
public class VipRegChannelController{
    @Autowired
    private VipRegChannelStatSumService vipRegChannelStatSumService;
    @Autowired
    private VipRegChannelStatDetailService vipRegChannelStatDetailService;
    @Autowired
    private PartnerMemberConService partnerMemberConService;

    @RequestMapping(value = "/showsum")
    @RequiresPermissions(value = {"game:operative:vipregchannel"})
    public ModelAndView showSum(ModelMap modelMap, int countryId){
        modelMap.put("countryId", countryId);
        ModelAndView view = new ModelAndView();
        view.setViewName("operative/game/operative/vipregchannel/sum.main");
        return view;
    }

    @RequestMapping(value = "/listsum", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"game:operative:vipregchannel"})
    @ResponseBody
    public String listSum(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        Object order = request.getParameter("order");
        Object orderField = request.getParameter("orderField");
        conditionMap.put("countryId", request.getParameter("countryId"));
        conditionMap.put("channel", request.getParameter("channel"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        conditionMap.put("payAmountStart", request.getParameter("payAmountStart"));
        conditionMap.put("payAmountEnd", request.getParameter("payAmountEnd"));
        conditionMap.put("order", order);
        conditionMap.put("orderField", orderField);
        result = vipRegChannelStatSumService.find(conditionMap, pageNumber, pageSize).toString();
        return result;
    }


    @RequestMapping(value = "/showdetail")
    @RequiresPermissions(value = {"game:operative:vipregchannel"})
    public ModelAndView showDetail(ModelMap modelMap, HttpServletRequest request){
        modelMap.put("countryId", request.getParameter("countryId"));
        modelMap.put("channel", request.getParameter("channel"));
        modelMap.put("createTimeStart", request.getParameter("createTimeStart"));
        modelMap.put("createTimeEnd", request.getParameter("createTimeEnd"));
        modelMap.put("platformInfos", vipRegChannelStatDetailService.getPlatformInfos());
        ModelAndView view = new ModelAndView();
        view.setViewName("operative/game/operative/vipregchannel/detail");
        return view;
    }

    @RequestMapping(value = "/listdetail", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"game:operative:vipregchannel"})
    @ResponseBody
    public String listDetail(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("countryId", request.getParameter("countryId"));
        conditionMap.put("channel", request.getParameter("channel"));
        conditionMap.put("createTimeStart", request.getParameter("createTimeStart"));
        conditionMap.put("createTimeEnd", request.getParameter("createTimeEnd"));
        conditionMap.put("payDateStart", request.getParameter("payDateStart"));
        conditionMap.put("payDateEnd", request.getParameter("payDateEnd"));
        conditionMap.put("payFirstAmountStart", request.getParameter("payFirstAmountStart"));
        conditionMap.put("payFirstAmountEnd", request.getParameter("payFirstAmountEnd"));
        conditionMap.put("payPlatformId", request.getParameter("payPlatformId"));
        conditionMap.put("name", request.getParameter("name"));
        conditionMap.put("isVip", request.getParameter("isVip"));
        conditionMap.put("platformName", request.getParameter("platformName"));
        result = vipRegChannelStatDetailService.find(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    @RequestMapping(value = "/showpartnerinfo")
    @RequiresPermissions(value = {"game:operative:vipregchannel"})
    public ModelAndView showPartnerInfo(ModelMap modelMap, String memberId){
        modelMap.put("partnerInfo", partnerMemberConService.findById(memberId));
        ModelAndView view = new ModelAndView();
        view.setViewName("operative/game/operative/vipregchannel/partnerinfo");
        return view;
    }

}
