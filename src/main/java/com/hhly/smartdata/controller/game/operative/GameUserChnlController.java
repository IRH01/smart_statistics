package com.hhly.smartdata.controller.game.operative;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.Logical;
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
import com.hhly.smartdata.service.game.GameUserChnlService;
import com.hhly.smartdata.util.DateListUtil;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.FileUtil;
import com.hhly.smartdata.util.HourListUtil;
import com.hhly.smartdata.util.page.Page;

@Controller
@RequestMapping(value="/game/operative/gameUserChnl")
public class GameUserChnlController {
	@Autowired
	private GameUserChnlService gameUserChnlService;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"game:operative:user:chnl"})
	public ModelAndView show(@ModelAttribute Page page) {
        Map<String,Object> model = Maps.newHashMap();
        model.put("produces", gameUserChnlService.getProduces(null));
        return  new ModelAndView("/operative/game/operative/game_user_chnl.main" , model);
	}
	
	@RequestMapping(value="/list",produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value={"game:operative:user:chnl"})
	@ResponseBody
	public String list(int pageNumber,int pageSize,HttpServletRequest request){
		String result = "";
		Map<String, Object> conditionMap = getParam(request);
		result = gameUserChnlService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	public Map<String, Object> getParam(HttpServletRequest request) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		String userType = request.getParameter("userType");
		if(userType == null || "".equals(userType.trim())) {
			//默认注册用户
			userType = "1";
		}
		conditionMap.put("userType", userType);
		
		
		String channelId = request.getParameter("channelId");
		if(channelId == null || "".equals(channelId.trim())) {
			//查询所有渠道
			channelId = "-1";
		}
		conditionMap.put("channelId", channelId);
		
		
		String dateStart = request.getParameter("dateStart");
		String dateEnd = request.getParameter("dateEnd");
		//开始时间和结束时间都为null，那么查询当天的数据
		if((dateStart == null || "".equals(dateStart.trim())) && (dateEnd == null || "".equals(dateEnd.trim()))) {
			String date = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
			dateStart = date;
			dateEnd = date;
		}
		if(dateStart.equals(dateEnd)) {
			conditionMap.put("type", "H");
			TreeSet<String> scales = HourListUtil.getHourListPerHour();
			conditionMap.put("scales", scales);
		} else {
			TreeSet<String> scales = DateListUtil.getCountDateList(dateStart, dateEnd);
			
//			scales.remove(scales.first());
			scales.remove(scales.last());
			
			conditionMap.put("scales", scales);
			conditionMap.put("type", "D");
		}
		conditionMap.put("dateStart", dateStart);
		conditionMap.put("dateEnd", dateEnd);
		
		
		String platformId = request.getParameter("platformId");
		if(platformId == null || "".equals(platformId.trim())) {
			//查询所有产品
			platformId = "-1";
		}
		conditionMap.put("platformId", platformId);
		
		
		User  user = (User)request.getSession().getAttribute(SysConstant.SESSION_USER);
		conditionMap.put("userId", user.getUserId()); 
		return conditionMap;
	}
	
	@RequestMapping(value="/chart")
	@ResponseBody
	@RequiresPermissions(value={"game:operative:user:chnl"},logical=Logical.OR)
	public String getChart(HttpServletRequest request){
		
		String result = "";
		Map<String, Object> conditionMap = getParam(request);
		result = gameUserChnlService.getChart(conditionMap).toString();
		return result;
	} 
	
	@RequiresPermissions(value={"game:operative:user:chnl"})
	@RequestMapping(value="/export")
	@ResponseBody
	public Object export(HttpServletRequest request,HttpSession session){
		Map<String, Object> conditionMap = getParam(request);
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(!gameUserChnlService.canExport(conditionMap)){
			result.put("canExport", false);
			return result;
		}
		String filePath = gameUserChnlService.export(conditionMap);
		String uuid = UUID.randomUUID().toString();
		session.setAttribute(uuid, filePath);
		result.put("canExport", true);
		result.put("uuid", uuid);
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:user:chnl"})
	@RequestMapping(value="/download")
	public void download(String uuid, HttpServletResponse response, HttpSession session){
		String filePath = (String)session.getAttribute(uuid);
		FileUtil.downLoadFile(response, new File(filePath), "用户分析");
		FileUtil.delete(filePath);
	}
	
}
