package com.hhly.smartdata.controller.game;

import java.util.TreeSet;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.mapper.game.TerminalTypeReposity;
import com.hhly.smartdata.service.game.GameStatDailyService;
import com.hhly.smartdata.service.game.GameSummaryDailyService;
import com.hhly.smartdata.service.game.GameSummaryHourlyService;
import com.hhly.smartdata.util.DateListUtil;
import com.hhly.smartdata.util.HourListUtil;

/*
 * 新增用户分析
 */
@Controller
@RequestMapping(value="/game/newuserals")
public class NewUserAnalysisController {
	@Autowired
	private GameSummaryDailyService gameSummaryDailyService;
	@Autowired
	private GameSummaryHourlyService gameSummaryHourlyService;
	@Autowired
	private TerminalTypeReposity terminalTypeReposity;
	@Autowired
	private GameStatDailyService gameStatDailyService;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"gameguess:newuserals","peoluckywheel:newuserals"},logical=Logical.OR)
	public ModelAndView show(String platformId,ModelMap modelMap){
		ModelAndView view = new ModelAndView();
		modelMap.put("platformId", platformId);
		modelMap.put("deviceTypes", terminalTypeReposity.getAll());
		view.setViewName("operative/game/new_user_analysis.main");
		return view;
	}
	
	@RequestMapping(value="/chart")
	@ResponseBody
	@RequiresPermissions(value={"gameguess:newuserals","peoluckywheel:newuserals"},logical=Logical.OR)
	public String getChart(String platformId,String startDate,String endDate,String deviceTypes){
		String result = "";
		if (startDate.equals(endDate)) {
			TreeSet<String> scales = HourListUtil.getHourListPerHour();
			result = gameSummaryHourlyService.getChart(platformId, startDate, deviceTypes, scales).toString();
		}else{
			TreeSet<String> scales = DateListUtil.getCountDateList(startDate, endDate);
			result = gameSummaryDailyService.getChart(platformId, startDate, endDate, deviceTypes, scales).toString();
		}
		return result;
	} 
	
	@RequestMapping(value="/list")
	@ResponseBody
	@RequiresPermissions(value={"gameguess:newuserals","peoluckywheel:newuserals"},logical=Logical.OR)
	public String getList(String platformId,String startDate,String endDate,int pageNumber, int pageSize){
		return gameStatDailyService.statistics(platformId, startDate, endDate,pageNumber, pageSize).toString();
	}
	
	@RequestMapping(value="/detail",method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
	@ResponseBody
	@RequiresPermissions(value={"gameguess:newuserals","peoluckywheel:newuserals"},logical=Logical.OR)
	public String getDetail(String platformId,String date){
		String value = gameStatDailyService.find(platformId, date).toString();
		return value;
	}
}
