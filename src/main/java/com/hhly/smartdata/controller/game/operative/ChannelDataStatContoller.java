package com.hhly.smartdata.controller.game.operative;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.StringUtil;
import com.hhly.smartdata.constant.Constants;
import com.hhly.smartdata.service.game.operative.GameChannelDailyService;
import com.hhly.smartdata.service.game.operative.GamePlatformDailyService;
import com.hhly.smartdata.service.game.operative.GameTotalDailyService;
import com.hhly.smartdata.util.ToolUtil;

@Controller
@RequestMapping(value="/game/operative/channeldatastat")
public class ChannelDataStatContoller {
	@Autowired
	GameTotalDailyService gameTotalDailyService;
	@Autowired
	GamePlatformDailyService gamePlatformDailyService;
	@Autowired
	GameChannelDailyService gameChannelDailyService;
	
	@RequiresPermissions(value={"game:operative:gamechanneldatastat","th:game:operative:gamechanneldatastat","vn:game:operative:gamechanneldatastat","my:game:operative:gamechanneldatastat","sg:game:operative:gamechanneldatastat"},logical=Logical.OR)
	@RequestMapping(value="/show")
	public ModelAndView show(ModelMap modelMap,String countryId){
		isPermitted(countryId);
		ModelAndView view = new ModelAndView();
		modelMap.put("countryId", countryId);
		//货币单位
		modelMap.put("currencyUnit", ToolUtil.getCurrencyUnit(countryId));
		view.setViewName("operative/game/operative/channel_data_statistic.main");
		return view;
	}
	
	@RequiresPermissions(value={"game:operative:gamechanneldatastat","th:game:operative:gamechanneldatastat","vn:game:operative:gamechanneldatastat","my:game:operative:gamechanneldatastat","sg:game:operative:gamechanneldatastat"},logical=Logical.OR)
	@RequestMapping(value="/dailydatalist")
	@ResponseBody
	public String getDailyDataList(HttpServletRequest request,int pageNumber,int pageSize){
		isPermitted(request.getParameter("countryId"));
		String result = null;
		Object dateStart = request.getParameter("dateStart");
		Object dateEnd = request.getParameter("dateEnd");
		Object order = request.getParameter("order");
		Object orderField = request.getParameter("orderField");
		if(false == validDailyOrderParam(orderField)){
			return null;
		}
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("countryId", request.getParameter("countryId"));
		conditionMap.put("dateStart", dateStart);
		conditionMap.put("dateEnd", dateEnd);
		conditionMap.put("order", order);
		conditionMap.put("orderField", orderField);
		result = gameTotalDailyService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:gamechanneldatastat","th:game:operative:gamechanneldatastat","vn:game:operative:gamechanneldatastat","my:game:operative:gamechanneldatastat","sg:game:operative:gamechanneldatastat"},logical=Logical.OR)
	@RequestMapping(value="/gamedatalist",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getGameDataList(HttpServletRequest request,int pageNumber,int pageSize){
		isPermitted(request.getParameter("countryId"));
		String result = null;
		Object date = request.getParameter("date");
		Object order = request.getParameter("order");
		Object orderField = request.getParameter("orderField");
		if(false == validGameOrderParam(orderField)){
			return null;
		}
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("countryId", request.getParameter("countryId"));
		conditionMap.put("date", date);
		conditionMap.put("order", order);
		conditionMap.put("orderField", orderField);
		result = gamePlatformDailyService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:gamechanneldatastat","th:game:operative:gamechanneldatastat","vn:game:operative:gamechanneldatastat","my:game:operative:gamechanneldatastat","sg:game:operative:gamechanneldatastat"},logical=Logical.OR)
	@RequestMapping(value="/channeldatalist",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getChannelDataList(HttpServletRequest request,int pageNumber,int pageSize){
		String result = null;
		Object date = request.getParameter("date");
		Object gameId = request.getParameter("gameId");
		Object order = request.getParameter("order");
		Object orderField = request.getParameter("orderField");
		if(false == validChannelOrderParam(orderField)){
			return null;
		}
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("date", date);
		conditionMap.put("gameId", gameId);
		conditionMap.put("order", order);
		conditionMap.put("orderField", orderField);
		result = gameChannelDailyService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	/**
	 * 日期数据表字段
	 */
	private static List<String> DAILY_DATA_FILEDS = new ArrayList<String>(){
		private static final long serialVersionUID = -1834116347453637286L;
		{
			add("stat_date");
			add("reg_count");
			add("recharge_count");
			add("pay_amount");
			add("reg_total_count");
			add("dau");
			add("wau");
			add("mau");
		}
	};
	
	/**
	 * 游戏数据表字段
	 */
	private static List<String> GAME_DATA_FILEDS = new ArrayList<String>(){
		private static final long serialVersionUID = -406904786152348092L;

		{
			add("stat_date");
			add("reg_count");
			add("recharge_count");
			add("pay_amount");
			add("reg_total_count");
			add("dau");
			add("wau");
			add("mau");
			add("game_id");
			add("game_name");
		}
	};
	
	/**
	 * 渠道数据表字段
	 */
	private static List<String> CHANNEL_DATA_FILEDS = new ArrayList<String>(){
		private static final long serialVersionUID = -1722566853640504836L;

		{
			add("stat_date");
			add("reg_count");
			add("recharge_count");
			add("pay_amount");
			add("reg_total_count");
			add("dau");
			add("wau");
			add("mau");
			add("game_id");
			add("game_name");
			add("channel_id");
			add("channel_name");
		}
	};
	
	/**
	 * 日数据排序列名校验
	 * @param field
	 * @return
	 */
	private boolean validDailyOrderParam(Object field){
		String fieldString = ToolUtil.objectToString(field);
		if(StringUtil.isEmpty(fieldString)){
			return true;
		}
		
		if(DAILY_DATA_FILEDS.contains(fieldString)){
			return true;
		}
		return false;
	}
	
	/**
	 * 游戏数据排序列名校验
	 * @param field
	 * @return
	 */
	private boolean validGameOrderParam(Object field){
		String fieldString = ToolUtil.objectToString(field);
		if(StringUtil.isEmpty(fieldString)){
			return true;
		}
		
		if(GAME_DATA_FILEDS.contains(fieldString)){
			return true;
		}
		return false;
	}
	
	/**
	 * 渠道数据排序列名校验
	 * @param field
	 * @return
	 */
	private boolean validChannelOrderParam(Object field){
		String fieldString = ToolUtil.objectToString(field);
		if(StringUtil.isEmpty(fieldString)){
			return true;
		}
		
		if(CHANNEL_DATA_FILEDS.contains(fieldString)){
			return true;
		}
		return false;
	}
	
	/**
	 * 是否有权限访问对应国家数据
	 * @param countryId
	 * @return
	 */
	private void isPermitted(String countryId){
		boolean isPermit = false;
		Subject subject = SecurityUtils.getSubject();
		if(Constants.COUNTRY_CN.equals(countryId)){
			isPermit =  subject.isPermitted("game:operative:gamechanneldatastat");
		}else if(Constants.COUNTRY_TH.equals(countryId)){
			isPermit =  subject.isPermitted("th:game:operative:gamechanneldatastat");
			//泰国版本
		}else if(Constants.COUNTRY_VN.equals(countryId)){
			isPermit =  subject.isPermitted("vn:game:operative:gamechanneldatastat");
			//越南版本
		}else if(Constants.COUNTRY_MY.equals(countryId)){
			isPermit =  subject.isPermitted("my:game:operative:gamechanneldatastat");
			//马来西亚版本
		}else if(Constants.COUNTRY_SG.equals(countryId)){
			isPermit =  subject.isPermitted("sg:game:operative:gamechanneldatastat");
			//新加坡版本
		}
		if(!isPermit){
			 throw new AuthorizationException("Sorry,you have no Permission.");
		}
	}
}
