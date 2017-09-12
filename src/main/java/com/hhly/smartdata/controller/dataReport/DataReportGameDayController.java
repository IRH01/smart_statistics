package com.hhly.smartdata.controller.dataReport;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.constant.SysConstant;
import com.hhly.smartdata.service.game.operative.DataReportGameDayService;
import com.hhly.smartdata.util.page.Page;

@Controller
@RequestMapping(value="/dataReport/game/day")
public class DataReportGameDayController {
	
	@Autowired
	private DataReportGameDayService dataReportGameDayService;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"datareport:game:day"})
	public ModelAndView show(@ModelAttribute Page page) {
        Map<String,Object> model = Maps.newHashMap();
        return  new ModelAndView("/operative/dataReport/datareport_game_day.main" , model);
	}
	
	@RequestMapping(value="/list",produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value={"datareport:game:day"})
	@ResponseBody
	public String list(int pageNumber,int pageSize,HttpServletRequest request){
		String result = "";
		Map<String, Object> conditionMap = getParam(request);
		result = dataReportGameDayService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	public Map<String, Object> getParam(HttpServletRequest request) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("gameName", request.getParameter("gameName"));
		conditionMap.put("dateStart", request.getParameter("dateStart"));
		conditionMap.put("dateEnd", request.getParameter("dateEnd"));
		conditionMap.put("dataDate", request.getParameter("dataDate"));
		User  user = (User)request.getSession().getAttribute(SysConstant.SESSION_USER);
		conditionMap.put("userId", user.getUserId()); 
		return conditionMap;
	}
	
	
	@RequestMapping(value="/showList")
	@RequiresPermissions(value={"datareport:game:day"})
	public ModelAndView showList(@ModelAttribute Page page) {
        Map<String,Object> model = Maps.newHashMap();
        return  new ModelAndView("/operative/dataReport/datareport_game_day_list.main" , model);
	}
	
	@RequestMapping(value="/daylist",produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value={"datareport:game:day"})
	@ResponseBody
	public String daylist(int pageNumber,int pageSize,HttpServletRequest request) {
		String result = "";
		Map<String, Object> conditionMap = getParam(request);
		result = dataReportGameDayService.findList(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
}
