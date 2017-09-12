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
import com.hhly.smartdata.service.game.operative.DataReportPlatformRealtimeService;
import com.hhly.smartdata.util.page.Page;

@Controller
@RequestMapping(value="/dataReport/platform/realtime")
public class DataReportPlatformRealtimeController {
	
	@Autowired
	private DataReportPlatformRealtimeService dataReportPlatformRealtimeService;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"datareport:platform:realtime"})
	public ModelAndView show(@ModelAttribute Page page) {
        Map<String,Object> model = Maps.newHashMap();
        return  new ModelAndView("/operative/dataReport/dataReport-platform-realtime.main" , model);
	}
	
	@RequestMapping(value="/list",produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value={"datareport:platform:realtime"})
	@ResponseBody
	public String list(int pageNumber,int pageSize,HttpServletRequest request){
		String result = "";
		Map<String, Object> conditionMap = getParam(request);
		result = dataReportPlatformRealtimeService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	public Map<String, Object> getParam(HttpServletRequest request) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
//		conditionMap.put("userId1", request.getParameter("userId"));
//		conditionMap.put("nickName", request.getParameter("nickName"));
		User  user = (User)request.getSession().getAttribute(SysConstant.SESSION_USER);
		conditionMap.put("userId", user.getUserId()); 
		return conditionMap;
	}
}
