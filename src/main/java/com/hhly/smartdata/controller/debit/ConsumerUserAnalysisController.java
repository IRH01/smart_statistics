package com.hhly.smartdata.controller.debit;

import com.google.common.collect.Maps;
import com.hhly.smartdata.constant.SysConstant;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.game.operative.DebitConsumerUserService;
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
@RequestMapping(value = "/debit/consumer")
public class ConsumerUserAnalysisController{

    @Autowired
    private DebitConsumerUserService debitConsumerUserService;

    @RequestMapping(value = "/show")
    @RequiresPermissions(value = {"debit:consumeruser:detail"})
    public ModelAndView show(@ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/operative/debit/debit_consumeruser_detail.main", model);
    }

    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"debit:consumeruser:detail"})
    @ResponseBody
    public String list(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = getParam(request);
        result = debitConsumerUserService.find(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    public Map<String, Object> getParam(HttpServletRequest request){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("userId1", request.getParameter("userId"));
        conditionMap.put("nickName", request.getParameter("nickName"));
        conditionMap.put("rechargeType", request.getParameter("rechargeType"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        return conditionMap;
    }


    @RequiresPermissions(value = {"debit:consumeruser:detail"})
    @RequestMapping(value = "/export")
    @ResponseBody
    public Object export(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = getParam(request);

        Map<String, Object> result = new HashMap<String, Object>();
        if(!debitConsumerUserService.canExport(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = debitConsumerUserService.export(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"debit:consumeruser:detail"})
    @RequestMapping(value = "/download")
    public void download(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "消费明细");
        FileUtil.delete(filePath);
    }


    @RequestMapping(value = "/showList")
    @RequiresPermissions(value = {"debit:consumeruser:list"})
    public ModelAndView showList(@ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/operative/debit/debit_consumeruser_list.main", model);
    }

    @RequestMapping(value = "/listCon", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"debit:consumeruser:list"})
    @ResponseBody
    public String listCon(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = getParamList(request);
        result = debitConsumerUserService.findCon(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    public Map<String, Object> getParamList(HttpServletRequest request){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("orderId", request.getParameter("orderId"));
        conditionMap.put("userId1", request.getParameter("userId"));
        conditionMap.put("nickName", request.getParameter("nickName"));
        conditionMap.put("channelId", request.getParameter("channelId"));
        conditionMap.put("platformName", request.getParameter("platformName"));
        conditionMap.put("rechargeType", request.getParameter("rechargeType"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        return conditionMap;
    }


    @RequiresPermissions(value = {"debit:consumeruser:list"})
    @RequestMapping(value = "/exportList")
    @ResponseBody
    public Object exportList(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = getParamList(request);

        Map<String, Object> result = new HashMap<String, Object>();
        if(!debitConsumerUserService.canExportList(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = debitConsumerUserService.exportList(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"debit:consumeruser:list"})
    @RequestMapping(value = "/downloadList")
    public void downloadList(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "消费列表");
        FileUtil.delete(filePath);
    }
}
