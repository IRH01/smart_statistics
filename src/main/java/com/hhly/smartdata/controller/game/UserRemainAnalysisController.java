package com.hhly.smartdata.controller.game;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.service.game.RemainAnalysisDailyService;

@Controller
@RequestMapping(value="/game/userremainals")
public class UserRemainAnalysisController {
	@Autowired
	private RemainAnalysisDailyService remainAnalysisDailyService;
	
	@RequestMapping("/show")
	@RequiresPermissions(value={"gameguess:userremainals","peoluckywheel:userremainals"},logical=Logical.OR)
	public ModelAndView show(String platformId,ModelMap modelMap){
		ModelAndView view = new ModelAndView();
		modelMap.put("platformId", platformId);
		view.setViewName("operative/game/user_remain_analysis.main");
		return view;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	@RequiresPermissions(value={"gameguess:userremainals","peoluckywheel:userremainals"},logical=Logical.OR)
	public String getList(String platformId,String startDate,String endDate,int pageNumber, int pageSize){
		return remainAnalysisDailyService.find(platformId, startDate, endDate,pageNumber, pageSize).toString();
	}
}
