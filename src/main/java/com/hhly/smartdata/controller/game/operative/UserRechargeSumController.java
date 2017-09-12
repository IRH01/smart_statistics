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
import com.hhly.smartdata.service.game.operative.UserRechargeSummaryService;
import com.hhly.smartdata.util.FileUtil;
import com.hhly.smartdata.util.ToolUtil;

@Controller
@RequestMapping(value="/game/operative/userrechargesum")
public class UserRechargeSumController {
	@SuppressWarnings("unused")
	private final int ORDER_DESC = 1;
	private final int ORDER_ASC = 2;
	
	@Autowired
	CountryInfoService countryInfoService;
	@Autowired 
	PlatformInfoService platformInfoService;
	@Autowired
	UserRechargeSummaryService userRechargeSummaryService;
	@Autowired
	GameRechargeSummaryService gameRechargeSummaryService;
	@Autowired
	GameServerConfigService gameServerConfigService;
	@Autowired
	private PlatTerminalInfoService platTerminalInfoService;
	
	@RequiresPermissions(value={"game:operative:userrechargesum","th:game:operative:userrechargesum","vn:game:operative:userrechargesum","my:game:operative:userrechargesum","sg:game:operative:userrechargesum"},logical=Logical.OR)
	@RequestMapping(value="/show")
	public ModelAndView show(ModelMap modelMap,String countryId){
		isPermitted(countryId);
		modelMap.put("countryId", countryId);
		//modelMap.addAttribute("countries", countryInfoService.getExistDataCountryInfo());
		//modelMap.addAttribute("platforms", platformInfoService.getGamePlatformInfo());
		//充值到游戏汇总
		modelMap.addAttribute("gameRcgSummary", gameRechargeSummaryService.statisticsRcg2Game(countryId));
		//充值到乐盈币汇总
		modelMap.addAttribute("lybRcgSummary", gameRechargeSummaryService.statisticsRcg2Lyb(countryId));
		//剩余乐盈币统计
		modelMap.addAttribute("remainLybSummary", gameRechargeSummaryService.statisticsRemainLyb(countryId));
		ModelAndView view = new ModelAndView();
		//获取平台类型
		modelMap.put("platTerminalInfos", platTerminalInfoService.getAll());
		//货币单位
		modelMap.put("currencyUnit", ToolUtil.getCurrencyUnit(countryId));
		view.setViewName("operative/game/operative/user_recharge_summary.main");
		return view;
	}
	
	@RequiresPermissions(value={"game:operative:userrechargesum","th:game:operative:userrechargesum","vn:game:operative:userrechargesum","my:game:operative:userrechargesum","sg:game:operative:userrechargesum"},logical=Logical.OR)
	@RequestMapping(value="/list",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String list(int pageNumber,int pageSize,HttpServletRequest request){
		isPermitted(request.getParameter("countryId"));
		String result = "";
		Map<String, Object> conditionMap = getConditionMap(request);
		result = userRechargeSummaryService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:userrechargesum","th:game:operative:userrechargesum","vn:game:operative:userrechargesum","my:game:operative:userrechargesum","sg:game:operative:userrechargesum"},logical=Logical.OR)
	@RequestMapping(value="/export")
	@ResponseBody
	public Object export(HttpServletRequest request,HttpSession session){
		isPermitted(request.getParameter("countryId"));
		Map<String, Object> conditionMap = this.getConditionMap(request);
		Map<String, Object> result = new HashMap<String, Object>();
		if(!userRechargeSummaryService.canExport(conditionMap)){
			result.put("canExport", false);
			return result;
		}
		String filePath = userRechargeSummaryService.export(conditionMap);
		String uuid = UUID.randomUUID().toString();
		session.setAttribute(uuid, filePath);
		result.put("canExport", true);
		result.put("uuid", uuid);
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:userrechargesum","th:game:operative:userrechargesum","vn:game:operative:userrechargesum","my:game:operative:userrechargesum","sg:game:operative:userrechargesum"},logical=Logical.OR)
	@RequestMapping(value="/download")
	public void download(String uuid,HttpServletResponse response,HttpSession session){
		String filePath = (String)session.getAttribute(uuid);
		FileUtil.downLoadFile(response, new File(filePath), "用户充值汇总");
		FileUtil.delete(filePath);
	}
	
	@RequiresPermissions(value={"game:operative:userrechargesum","th:game:operative:userrechargesum","vn:game:operative:userrechargesum","my:game:operative:userrechargesum","sg:game:operative:userrechargesum"},logical=Logical.OR)
	@RequestMapping(value="/platforminfos",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getPlatInfos(int countryId,String platformTerminal){
		isPermitted(String.valueOf(countryId));
		String result = "";
		result = JSONArray.fromObject(platformInfoService.getGamePlatformInfo(countryId,platformTerminal)).toString();
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:userrechargesum","th:game:operative:userrechargesum","vn:game:operative:userrechargesum","my:game:operative:userrechargesum","sg:game:operative:userrechargesum"},logical=Logical.OR)
	@RequestMapping(value="/gameservers",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String getGameServerConfigs(int platformId){
		String result = "";
		result = gameServerConfigService.getByPlatformId(platformId).toString();
		return result;
	}
	
	private Map<String, Object>	getConditionMap(HttpServletRequest request){
		Map<String,Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("user", request.getParameter("user"));
		conditionMap.put("phone", request.getParameter("phone"));
		conditionMap.put("email", request.getParameter("email"));
		conditionMap.put("amountStart", request.getParameter("amountStart"));
		conditionMap.put("amountEnd", request.getParameter("amountEnd"));
		conditionMap.put("platformId", request.getParameter("platformId"));
		conditionMap.put("regTimeStart", request.getParameter("regTimeStart"));
		conditionMap.put("regTimeEnd", request.getParameter("regTimeEnd"));
		conditionMap.put("serverId", request.getParameter("serverId"));
		conditionMap.put("channelId", request.getParameter("channelId"));
		conditionMap.put("channelName", request.getParameter("channelName"));
		conditionMap.put("amountOrder", request.getParameter("amountOrder"));
		conditionMap.put("regTimeOrder", request.getParameter("regTimeOrder"));
		conditionMap.put("userIdOrder", ORDER_ASC);
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
			isPermit =  subject.isPermitted("game:operative:userrechargesum");
		}else if(Constants.COUNTRY_TH.equals(countryId)){
			isPermit =  subject.isPermitted("th:game:operative:userrechargesum");
			//泰国版本
		}else if(Constants.COUNTRY_VN.equals(countryId)){
			isPermit =  subject.isPermitted("vn:game:operative:userrechargesum");
			//越南版本
		}else if(Constants.COUNTRY_MY.equals(countryId)){
			isPermit =  subject.isPermitted("my:game:operative:userrechargesum");
			//马来西亚版本
		}else if(Constants.COUNTRY_SG.equals(countryId)){
			isPermit =  subject.isPermitted("sg:game:operative:userrechargesum");
			//新加坡版本
		}
		if(!isPermit){
			 throw new AuthorizationException("Sorry,you have no Permission.");
		}
	}
}
