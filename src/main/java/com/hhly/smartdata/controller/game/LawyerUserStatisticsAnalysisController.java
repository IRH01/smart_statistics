package com.hhly.smartdata.controller.game;

import com.google.common.collect.Maps;
import com.hhly.smartdata.constant.SysConstant;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.model.game.ChannelMonthAllbet;
import com.hhly.smartdata.service.game.operative.LawyerUserStatisticsService;
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

/*
 * 律师楼用户渠道充值消费报表
 */
@Controller
@RequestMapping(value = "/game/lawyerUserStatistics")
public class LawyerUserStatisticsAnalysisController{

    @Autowired
    private LawyerUserStatisticsService lawyerUserStatisticsService;

    @RequestMapping(value = "/show")
    @RequiresPermissions(value = {"game:operative:lawyer"})
    public ModelAndView show(@ModelAttribute ChannelMonthAllbet channelMonthAllbet, @ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/operative/game/channel_month_allbet.main", model);
    }

    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"game:operative:lawyer"})
    @ResponseBody
    public String list(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("channelId", request.getParameter("channelId"));
        //conditionMap.put("channelName", request.getParameter("channelName"));
        conditionMap.put("dateStart", request.getParameter("monthStart"));
        conditionMap.put("dateEnd", request.getParameter("monthEnd"));
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        result = lawyerUserStatisticsService.find(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    @RequiresPermissions(value = {"game:operative:lawyer"})
    @RequestMapping(value = "/export")
    @ResponseBody
    public Object export(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("channelId", request.getParameter("channelId"));
        //conditionMap.put("channelName", request.getParameter("channelName"));
        conditionMap.put("dateStart", request.getParameter("monthStart"));
        conditionMap.put("dateEnd", request.getParameter("monthEnd"));

        Map<String, Object> result = new HashMap<String, Object>();
        if(!lawyerUserStatisticsService.canExport(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = lawyerUserStatisticsService.export(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"game:operative:lawyer"})
    @RequestMapping(value = "/exportMonth")
    @ResponseBody
    public Object exportMonth(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("channelId", request.getParameter("channelId"));
        conditionMap.put("rechargeMonth", request.getParameter("rechargeMonth"));

        Map<String, Object> result = new HashMap<String, Object>();
        if(!lawyerUserStatisticsService.canExportMonth(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = lawyerUserStatisticsService.exportMonth(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"game:operative:lawyer"})
    @RequestMapping(value = "/exportDay")
    @ResponseBody
    public Object exportDay(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("channelId", request.getParameter("channelId"));
        conditionMap.put("rechargeMonth", request.getParameter("rechargeMonth"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));

        Map<String, Object> result = new HashMap<String, Object>();
        if(!lawyerUserStatisticsService.canExportDay(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = lawyerUserStatisticsService.exportDay(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"game:operative:lawyer"})
    @RequestMapping(value = "/download")
    public void download(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "律师楼用户渠道充值消费汇总报表");
        FileUtil.delete(filePath);
    }

    @RequiresPermissions(value = {"game:operative:lawyer"})
    @RequestMapping(value = "/downloadMonth")
    public void downloadMonth(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "渠道充值消费汇总报表");
        FileUtil.delete(filePath);
    }

    @RequiresPermissions(value = {"game:operative:lawyer"})
    @RequestMapping(value = "/downloadDay")
    public void downloadDay(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "渠道充值消费日报表");
        FileUtil.delete(filePath);
    }


    @RequestMapping(value = "/showMonth")
    @RequiresPermissions(value = {"game:operative:lawyer"})
    public ModelAndView showMonth(@ModelAttribute ChannelMonthAllbet channelMonthAllbet, @ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
//        model.put("channelId", channelMonthAllbet.getChannelId());
//        model.put("rechargeMonth", channelMonthAllbet.getRechargeMonth());
        return new ModelAndView("/operative/game/channel_month_sum.main", model);
    }

    @RequestMapping(value = "/listMonth", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"game:operative:lawyer"})
    @ResponseBody
    public String listMonth(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("channelId", request.getParameter("channelId"));
        conditionMap.put("rechargeMonth", request.getParameter("rechargeMonth"));
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        result = lawyerUserStatisticsService.findMonth(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    @RequestMapping(value = "/showDay")
    @RequiresPermissions(value = {"game:operative:lawyer"})
    public ModelAndView showDay(@ModelAttribute ChannelMonthAllbet channelMonthAllbet, @ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
//        model.put("channelId", channelMonthAllbet.getChannelId());
//        model.put("rechargeMonth", channelMonthAllbet.getRechargeMonth());
        return new ModelAndView("/operative/game/channel_day_sum.main", model);
    }

    @RequestMapping(value = "/listDay", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"game:operative:lawyer"})
    @ResponseBody
    public String listDay(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("channelId", request.getParameter("channelId"));
        conditionMap.put("rechargeMonth", request.getParameter("rechargeMonth"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        result = lawyerUserStatisticsService.findDay(conditionMap, pageNumber, pageSize).toString();
        return result;
    }
}
