package com.hhly.smartdata.controller.game.operative.lmdz;

import com.hhly.smartdata.constant.SysConstant;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.game.operative.lmdz.LmdzChannelDailyService;
import com.hhly.smartdata.util.FileUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
@RequestMapping(value = "/game/operative/lmdz/channeldata")
public class LmdzChannelDailyController{
    @Autowired
    private LmdzChannelDailyService lmdzChannelDailyService;

    @RequiresPermissions(value = {"game:operative:lmdz:channeldata"})
    @RequestMapping(value = "/show")
    public ModelAndView show(ModelMap modelMap, HttpServletRequest request){
        ModelAndView view = new ModelAndView();
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        modelMap.put("grades", lmdzChannelDailyService.getGrades(conditionMap));
        modelMap.put("oss", lmdzChannelDailyService.getOss(conditionMap));
        view.setViewName("operative/game/operative/lmdz/lmdz_channel_daily.main");
        return view;
    }

    @RequiresPermissions(value = {"game:operative:lmdz:channeldata"})
    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String list(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("grade", request.getParameter("grade"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        conditionMap.put("os", request.getParameter("os"));
        conditionMap.put("channelId", request.getParameter("channelId"));
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        result = JSONObject.fromObject(lmdzChannelDailyService.find(conditionMap, pageNumber, pageSize)).toString();
        return result;
    }

    @RequiresPermissions(value = {"game:operative:lmdz:channeldata"})
    @RequestMapping(value = "/sum", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String sum(HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("grade", request.getParameter("grade"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        conditionMap.put("os", request.getParameter("os"));
        conditionMap.put("channelId", request.getParameter("channelId"));
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        result = JSONObject.fromObject(lmdzChannelDailyService.sum(conditionMap)).toString();
        return result;
    }

    private Map<String, Object> getConditionMap(HttpServletRequest request){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("grade", request.getParameter("grade"));
        conditionMap.put("dateStart", request.getParameter("dateStart"));
        conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        conditionMap.put("os", request.getParameter("os"));
        conditionMap.put("channelId", request.getParameter("channelId"));
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        return conditionMap;
    }

    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/export")
    @ResponseBody
    public Object export(HttpServletRequest request, HttpSession session){
        Map<String, Object> conditionMap = this.getConditionMap(request);
        Map<String, Object> result = new HashMap<String, Object>();
        if(!lmdzChannelDailyService.canExport(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = lmdzChannelDailyService.export(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"partner:checkreport"})
    @RequestMapping(value = "/download")
    public void download(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "撩妹德州市场合作渠道数据");
        FileUtil.delete(filePath);
    }
}
