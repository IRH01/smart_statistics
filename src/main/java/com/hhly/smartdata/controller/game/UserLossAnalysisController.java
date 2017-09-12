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

import com.hhly.smartdata.service.game.ActiveLossDailyService;
import com.hhly.smartdata.service.game.PayLossDailyService;
import com.hhly.smartdata.util.DateListUtil;

@Controller
@RequestMapping(value="/game/userlossals")
public class UserLossAnalysisController {
	@Autowired
	private ActiveLossDailyService activeLossDailyService;
	@Autowired
	private PayLossDailyService payLossDailyService;
	
	//活跃玩家类型
	private final static int TYPE_ACTIVE = 0;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"gameguess:userlossals","peoluckywheel:userlossals"},logical=Logical.OR)
	public ModelAndView show(String platformId,ModelMap modelMap){
		ModelAndView view = new ModelAndView();
		modelMap.put("platformId", platformId);
		view.setViewName("operative/game/user_loss_analysis.main");
		return view;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	@RequiresPermissions(value={"gameguess:userlossals","peoluckywheel:userlossals"},logical=Logical.OR)
	public String getList(String platformId,int type,String startDate,String endDate,int pageNumber, int pageSize){
		String result = "";
		//活跃玩家
		if (TYPE_ACTIVE == type) {
			result =  activeLossDailyService.find(platformId, startDate, endDate,pageNumber, pageSize).toString();
		}else{
			result =  payLossDailyService.find(platformId, startDate, endDate,pageNumber, pageSize).toString();
		}
		return result;
	}
	
	@RequestMapping(value="/chart")
	@ResponseBody
	@RequiresPermissions(value={"gameguess:userlossals","peoluckywheel:userlossals"},logical=Logical.OR)
	public String getChart(String platformId,int type,String startDate,String endDate){
		String result = "";
		TreeSet<String> scales = DateListUtil.getCountDateList(startDate, endDate);
		//活跃玩家
		if (TYPE_ACTIVE == type) {
			result = activeLossDailyService.getChart(platformId, startDate, endDate,scales).toString();
		}else{
			result = payLossDailyService.getChart(platformId, startDate, endDate, scales).toString();
		}
		return result;
	} 
}
