package com.hhly.smartdata.controller.debit;

import com.google.common.collect.Maps;
import com.hhly.smartdata.constant.SysConstant;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.game.operative.DebitGameService;
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
@RequestMapping(value = "/debit/game")
public class DebitGameAnalysisController{

    @Autowired
    private DebitGameService debitGameService;

    @RequestMapping(value = "/show")
    @RequiresPermissions(value = {"debit:game:list"})
    public ModelAndView show(@ModelAttribute Page page){
        Map<String, Object> model = Maps.newHashMap();
        return new ModelAndView("/operative/debit/debit_game_list.main", model);
    }

    @RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
    @RequiresPermissions(value = {"debit:game:list"})
    @ResponseBody
    public String list(int pageNumber, int pageSize, HttpServletRequest request){
        String result = "";
        Map<String, Object> conditionMap = getParam(request);
        User user = (User) request.getSession().getAttribute(SysConstant.SESSION_USER);
        conditionMap.put("userId", user.getUserId());
        result = debitGameService.find(conditionMap, pageNumber, pageSize).toString();
        return result;
    }

    public Map<String, Object> getParam(HttpServletRequest request){
        Map<String, Object> conditionMap = new HashMap<String, Object>();
        conditionMap.put("orderId", request.getParameter("orderId"));
        conditionMap.put("userId1", request.getParameter("userId"));
        conditionMap.put("nickName", request.getParameter("nickName"));
        conditionMap.put("exchangeGame", request.getParameter("exchangeGame"));
        conditionMap.put("exchangeToGame", request.getParameter("exchangeToGame"));
//		conditionMap.put("dateStart", request.getParameter("dateStart"));
//		conditionMap.put("dateEnd", request.getParameter("dateEnd"));
        return conditionMap;
    }

    @RequiresPermissions(value = {"debit:game:list"})
    @RequestMapping(value = "/export")
    @ResponseBody
    public Object export(HttpServletRequest request, HttpSession session){

        Map<String, Object> conditionMap = getParam(request);
        Map<String, Object> result = new HashMap<String, Object>();
        if(!debitGameService.canExport(conditionMap)){
            result.put("canExport", false);
            return result;
        }
        String filePath = debitGameService.export(conditionMap);
        String uuid = UUID.randomUUID().toString();
        session.setAttribute(uuid, filePath);
        result.put("canExport", true);
        result.put("uuid", uuid);
        return result;
    }

    @RequiresPermissions(value = {"debit:game:list"})
    @RequestMapping(value = "/download")
    public void download(String uuid, HttpServletResponse response, HttpSession session){
        String filePath = (String) session.getAttribute(uuid);
        FileUtil.downLoadFile(response, new File(filePath), "游戏划账列表");
        FileUtil.delete(filePath);
    }
}
