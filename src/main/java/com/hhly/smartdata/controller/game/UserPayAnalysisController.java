package com.hhly.smartdata.controller.game;

import java.util.TreeSet;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.service.game.GameStatDailyService;
import com.hhly.smartdata.service.game.GameTransDailyService;
import com.hhly.smartdata.service.game.GameTransHourlyService;
import com.hhly.smartdata.util.DateListUtil;
import com.hhly.smartdata.util.HourListUtil;

/*
 * 用户付费分析
 */
@Controller
@RequestMapping(value="/game/userpayals")
public class UserPayAnalysisController {
	@Autowired
	GameTransDailyService gameTransDailyService;
	@Autowired
	GameTransHourlyService gameTransHourlyService;
	@Autowired
	GameStatDailyService gameStatDailyService;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"gameguess:userpayals","peoluckywheel:userpayals"},logical=Logical.OR)
	public ModelAndView show(String platformId,ModelMap modelMap){
		ModelAndView view = new ModelAndView();
		modelMap.put("platformId", platformId);
		view.setViewName("operative/game/user_pay_analysis.main");
		return view;
	}
	
	@RequestMapping(value="chart")
	@ResponseBody
	@RequiresPermissions(value={"gameguess:userpayals","peoluckywheel:userpayals"},logical=Logical.OR)
	public String getChart(String platformId,String startDate,String endDate){
		if(startDate.equalsIgnoreCase(endDate)){
			TreeSet<String> scales = HourListUtil.getHourListPerHour();
			return gameTransHourlyService.getChart(platformId, startDate, scales).toString();
		}else {
			TreeSet<String> scales = DateListUtil.getCountDateList(startDate, endDate);
			return gameTransDailyService.getChart(platformId, startDate, endDate, scales).toString();
		}
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	@RequiresPermissions(value={"gameguess:userpayals","peoluckywheel:userpayals"},logical=Logical.OR)
	public String getList(String platformId,String startDate,String endDate,int pageNumber, int pageSize){
		return gameStatDailyService.statistics(platformId, startDate, endDate,pageNumber, pageSize).toString();
	}
}
