package com.hhly.smartdata.controller.game.operative;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

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

import com.hhly.smartdata.constant.Constants;
import com.hhly.smartdata.service.game.CountryInfoService;
import com.hhly.smartdata.service.game.PlatformInfoService;
import com.hhly.smartdata.service.game.operative.GameRechargeSummaryService;
import com.hhly.smartdata.service.game.operative.GameServerConfigService;
import com.hhly.smartdata.service.game.operative.PlatTerminalInfoService;
import com.hhly.smartdata.service.game.operative.ViewOrderDetailService;
import com.hhly.smartdata.util.FileUtil;
import com.hhly.smartdata.util.ToolUtil;

@Controller
@RequestMapping(value="/game/operative/orderdetailsum")
public class OrderDetailSumController {
	
	private final int ORDER_DESC = 1;
	@SuppressWarnings("unused")
	private final int ORDER_ASC = 2;
	
	@Autowired
	CountryInfoService countryInfoService;
	@Autowired 
	PlatformInfoService platformInfoService;
	@Autowired
	ViewOrderDetailService viewOrderDetailService;
	@Autowired
	GameRechargeSummaryService gameRechargeSummaryService;
	@Autowired
	GameServerConfigService gameServerConfigService;
	@Autowired
	private PlatTerminalInfoService platTerminalInfoService;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"game:operative:orderdetailsum","th:game:operative:orderdetailsum","vn:game:operative:orderdetailsum","my:game:operative:orderdetailsum","sg:game:operative:orderdetailsum"},logical=Logical.OR)
	public ModelAndView show(ModelMap modelMap,String countryId){
		ModelAndView view = new ModelAndView();
		modelMap.put("countryId", countryId);
		//modelMap.addAttribute("platforms", platformInfoService.getGamePlatformInfo());
		//modelMap.addAttribute("countries", countryInfoService.getExistDataCountryInfo());
		//充值到游戏汇总
		modelMap.addAttribute("gameRcgSummary", gameRechargeSummaryService.statisticsRcg2Game(countryId));
		//充值到乐盈币汇总
		modelMap.addAttribute("lybRcgSummary", gameRechargeSummaryService.statisticsRcg2Lyb(countryId));
		//剩余乐盈币统计
		modelMap.addAttribute("remainLybSummary", gameRechargeSummaryService.statisticsRemainLyb(countryId));
		isPermitted(countryId);
		//获取平台类型
		modelMap.put("platTerminalInfos", platTerminalInfoService.getAll());
		//货币单位
		modelMap.put("currencyUnit", ToolUtil.getCurrencyUnit(countryId));
		view.setViewName("operative/game/operative/order_detail_summary.main");
		return view;
	}
	
	@RequiresPermissions(value={"game:operative:orderdetailsum","th:game:operative:orderdetailsum","vn:game:operative:orderdetailsum","my:game:operative:orderdetailsum","sg:game:operative:orderdetailsum"},logical=Logical.OR)
	@RequestMapping(value="/list",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String list(int pageNumber,int pageSize,HttpServletRequest request){
		isPermitted(request.getParameter("countryId"));
		String result = "";
		Map<String, Object> conditionMap = getConditionMap(request);
		result = viewOrderDetailService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:orderdetailsum","th:game:operative:orderdetailsum","vn:game:operative:orderdetailsum","my:game:operative:orderdetailsum","sg:game:operative:orderdetailsum"},logical=Logical.OR)
	@RequestMapping(value="/platforminfos",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getPlatInfos(int countryId,String platformTerminal){
		isPermitted(String.valueOf(countryId));
		String result = "";
		result = JSONArray.fromObject(platformInfoService.getGamePlatformInfo(countryId,platformTerminal)).toString();
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:orderdetailsum","th:game:operative:orderdetailsum","vn:game:operative:orderdetailsum","my:game:operative:orderdetailsum","sg:game:operative:orderdetailsum"},logical=Logical.OR)
	@RequestMapping(value="/gameservers",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getGameServerConfigs(int platformId){
		String result = "";
		result = gameServerConfigService.getByPlatformId(platformId).toString();
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:orderdetailsum","th:game:operative:orderdetailsum","vn:game:operative:orderdetailsum","my:game:operative:orderdetailsum","sg:game:operative:orderdetailsum"},logical=Logical.OR)
	@RequestMapping(value="/export")
	@ResponseBody
	public Object export(HttpServletRequest request,HttpSession session){
		isPermitted(request.getParameter("countryId"));
		Map<String, Object> conditionMap = this.getConditionMap(request);
		Map<String, Object> result = new HashMap<String, Object>();
		if(!viewOrderDetailService.canExport(conditionMap)){
			result.put("canExport", false);
			return result;
		}
		String filePath = viewOrderDetailService.export(conditionMap);
		String uuid = UUID.randomUUID().toString();
		session.setAttribute(uuid, filePath);
		result.put("canExport", true);
		result.put("uuid", uuid);
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:orderdetailsum","th:game:operative:orderdetailsum","vn:game:operative:orderdetailsum","my:game:operative:orderdetailsum","sg:game:operative:orderdetailsum"},logical=Logical.OR)
	@RequestMapping(value="/download")
	public void download(String uuid,HttpServletResponse response,HttpSession session){
		String filePath = (String)session.getAttribute(uuid);
		FileUtil.downLoadFile(response, new File(filePath), "订单详情汇总");
		FileUtil.delete(filePath);
	}
	
	private Map<String, Object>	getConditionMap(HttpServletRequest request){
		Map<String,Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("orderNo", request.getParameter("orderNo"));
		conditionMap.put("user", request.getParameter("user"));
		conditionMap.put("amountStart", request.getParameter("amountStart"));
		conditionMap.put("amountEnd", request.getParameter("amountEnd"));
		conditionMap.put("platformId", request.getParameter("platformId"));
		conditionMap.put("serverId", request.getParameter("serverId"));
		conditionMap.put("createTimeStart", request.getParameter("createTimeStart"));
		conditionMap.put("createTimeEnd", request.getParameter("createTimeEnd"));
		conditionMap.put("rechargeWay", request.getParameter("rechargeWay"));
		conditionMap.put("payStatus", request.getParameter("payStatus"));
		conditionMap.put("payTimeStart", request.getParameter("payTimeStart"));
		conditionMap.put("payTimeEnd", request.getParameter("payTimeEnd"));
		conditionMap.put("channelId", request.getParameter("channelId"));
		conditionMap.put("developers", request.getParameter("developers"));
		conditionMap.put("orderType", request.getParameter("orderType"));
		conditionMap.put("payTimeOrder", request.getParameter("payTimeOrder"));
		conditionMap.put("amountOrder", request.getParameter("amountOrder"));
		conditionMap.put("createTimeOrder", request.getParameter("createTimeOrder"));
		conditionMap.put("noOrder", ORDER_DESC);
		conditionMap.put("paymentOrderNo", request.getParameter("paymentOrderNo"));
		conditionMap.put("tradeNo", request.getParameter("tradeNo"));
		conditionMap.put("countryId", request.getParameter("countryId"));
		conditionMap.put("platformTerminal", request.getParameter("platformTerminal"));
		return conditionMap;
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
			isPermit =  subject.isPermitted("game:operative:orderdetailsum");
		}else if(Constants.COUNTRY_TH.equals(countryId)){
			isPermit =  subject.isPermitted("th:game:operative:orderdetailsum");
			//泰国版本
		}else if(Constants.COUNTRY_VN.equals(countryId)){
			isPermit =  subject.isPermitted("vn:game:operative:orderdetailsum");
			//越南版本
		}else if(Constants.COUNTRY_MY.equals(countryId)){
			isPermit =  subject.isPermitted("my:game:operative:orderdetailsum");
			//马来西亚版本
		}else if(Constants.COUNTRY_SG.equals(countryId)){
			isPermit =  subject.isPermitted("sg:game:operative:orderdetailsum");
			//新加坡版本
		}
		if(!isPermit){
			 throw new AuthorizationException("Sorry,you have no Permission.");
		}
	}
}
