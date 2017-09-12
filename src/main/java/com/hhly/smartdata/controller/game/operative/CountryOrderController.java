package com.hhly.smartdata.controller.game.operative;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.hhly.smartdata.service.game.operative.CountryOrderService;
import com.hhly.smartdata.service.game.operative.CountryPlatformService;
import com.hhly.smartdata.service.game.operative.PlatTerminalInfoService;

@Controller
@RequestMapping(value="/game/operative/countryrcg")
public class CountryOrderController {
	@Autowired
	private CountryOrderService countryOrderService;
	@Autowired
	private CountryPlatformService countryPlatformService;
	@Autowired
	private PlatTerminalInfoService platTerminalInfoService;
	
	@RequestMapping(value="/showcountryrcg")
	@RequiresPermissions(value={"game:operative:countryrcg"})
	public ModelAndView showCountryRcg(ModelMap modelMap){
		ModelAndView view = new ModelAndView();
		view.setViewName("operative/game/operative/countryrcg/country_order.main");
		return view;
	}
	
	@RequestMapping(value="/listcountryrcg",produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value={"game:operative:countryrcg"})
	@ResponseBody
	public String listCountryRcg(HttpServletRequest request){
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("dateStart", request.getParameter("dateStart"));
		conditionMap.put("dateEnd", request.getParameter("dateEnd"));
		return JSONArray.toJSONString(countryOrderService.find(conditionMap));
	}
	
	@RequestMapping(value="/showplatformrcg")
	@RequiresPermissions(value={"game:operative:countryrcg"})
	public ModelAndView showPlatformRcg(ModelMap modelMap,HttpServletRequest request){
		modelMap.put("platTerminalInfos", platTerminalInfoService.getAll());
		modelMap.put("dateStart", request.getParameter("dateStart"));
		modelMap.put("dateEnd", request.getParameter("dateEnd"));
		modelMap.put("country", request.getParameter("country"));
		ModelAndView view = new ModelAndView();
		view.setViewName("operative/game/operative/countryrcg/countryplatform");
		return view;
	}
	
	@RequestMapping(value="/listplatformrcg",produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value={"game:operative:countryrcg"})
	@ResponseBody
	public String listPlatformRcg(HttpServletRequest request,int pageNumber,int pageSize){
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("dateStart", request.getParameter("dateStart"));
		conditionMap.put("dateEnd", request.getParameter("dateEnd"));
		conditionMap.put("country", request.getParameter("country"));
		conditionMap.put("platformTerminal", request.getParameter("platformTerminal"));
		conditionMap.put("order", request.getParameter("order"));
		conditionMap.put("orderField", request.getParameter("orderField"));
		return JSONObject.fromObject(countryPlatformService.find(conditionMap, pageNumber, pageSize)).toString();
	}
}
