package com.hhly.smartdata.controller.channal;

import com.google.common.collect.Maps;
import com.hhly.smartdata.service.game.operative.AgentChannalService;
import com.hhly.smartdata.util.FileUtil;
import com.hhly.smartdata.util.page.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping(value = "/agent/channel")
public class AgentChannalController{

    @Autowired
    private AgentChannalService agentChannalService;

    @RequestMapping(value = "/show")
    @RequiresPermissions(value = {"agent:channel"})
    public ModelAndView show(@ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/operative/channal/agent_channal.main", model);
    }

    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"agent:channel"})
    @ResponseBody
    public String list(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = getParam(request);
        result = agentChannalService.find(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    public Map<String, Object> getParam(HttpServletRequest request){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("accountNo", request.getParameter("accountNo"));
        conditionMap.put("promotNo", request.getParameter("promotNo"));
        conditionMap.put("channelId", request.getParameter("channelId"));
        conditionMap.put("channelName", request.getParameter("channelName"));
        conditionMap.put("regCountStart", request.getParameter("regCountStart"));
        conditionMap.put("regCountEnd", request.getParameter("regCountEnd"));
        conditionMap.put("rechargeAmountStart", request.getParameter("rechargeAmountStart"));
        conditionMap.put("rechargeAmountEnd", request.getParameter("rechargeAmountEnd"));
        conditionMap.put("startTimeStart", request.getParameter("startTimeStart"));
        conditionMap.put("startTimeEnd", request.getParameter("startTimeEnd"));
        conditionMap.put("avgTimeStart", request.getParameter("avgTimeStart"));
        conditionMap.put("avgTimeEnd", request.getParameter("avgTimeEnd"));
        conditionMap.put("userId", request.getParameter("userId"));
        conditionMap.put("userName", request.getParameter("userName"));
        conditionMap.put("registAddress", request.getParameter("registAddress"));
        conditionMap.put("platformId", request.getParameter("platformId"));
        conditionMap.put("payType", request.getParameter("payType"));
        conditionMap.put("costAmountStart", request.getParameter("costAmountStart"));
        conditionMap.put("costAmountEnd", request.getParameter("costAmountEnd"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        return conditionMap;
    }

    @RequestMapping(value = "/showChannelDetails")
    @RequiresPermissions(value = {"agent:channel"})
    public ModelAndView showChannelDetails(@ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/operative/channal/agent_channal_details.main", model);
    }

    @RequestMapping(value = "/listChannelDetails", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"agent:channel"})
    @ResponseBody
    public String listChannelDetails(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = getParam(request);
        result = agentChannalService.findChannelDetails(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    @RequiresPermissions(value = {"agent:channel"})
    @RequestMapping(value = "/export")
    @ResponseBody
    public Object export(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = getParam(request);

        Map<String, Object> result = new HashMap<String, Object>();
        if(!agentChannalService.canExport(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = agentChannalService.export(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"agent:channel"})
    @RequestMapping(value = "/download")
    public void download(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "渠道详情");
        FileUtil.delete(filePath);
    }


    @RequestMapping(value = "/showChannelCostDetails")
    @RequiresPermissions(value = {"agent:channel"})
    public ModelAndView showChannelCostDetails(@ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/operative/channal/agent_channal_cost_details.main", model);
    }

    @RequestMapping(value = "/listChannelCostDetails", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"agent:channel"})
    @ResponseBody
    public String listChannelCostDetails(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = getParam(request);
        result = agentChannalService.findChannelCostDetails(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    @RequiresPermissions(value = {"agent:channel"})
    @RequestMapping(value = "/exportCost")
    @ResponseBody
    public Object exportCost(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = getParam(request);

        Map<String, Object> result = new HashMap<String, Object>();
        if(!agentChannalService.canExportCost(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = agentChannalService.exportCost(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"agent:channel"})
    @RequestMapping(value = "/downloadCost")
    public void downloadCost(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "投入详情");
        FileUtil.delete(filePath);
    }


    @RequestMapping(value = "/showChannelRegistDetails")
    @RequiresPermissions(value = {"agent:channel"})
    public ModelAndView showChannelRegistDetails(@ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/operative/channal/agent_channal_regist_details.main", model);
    }

    @RequestMapping(value = "/listChannelRegistDetails", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"agent:channel"})
    @ResponseBody
    public String listChannelRegistDetails(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = getParam(request);
        result = agentChannalService.findChannelRegistDetails(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    @RequiresPermissions(value = {"agent:channel"})
    @RequestMapping(value = "/exportRegist")
    @ResponseBody
    public Object exportRegist(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = getParam(request);

        Map<String, Object> result = new HashMap<String, Object>();
        if(!agentChannalService.canExportRegist(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = agentChannalService.exportRegist(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"agent:channel"})
    @RequestMapping(value = "/downloadRegist")
    public void downloadRegist(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "投入详情");
        FileUtil.delete(filePath);
    }
}
