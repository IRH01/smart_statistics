package com.hhly.smartdata.controller.game.operative.marketprtchn;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.constant.SysConstant;
import com.hhly.smartdata.service.game.operative.CooperationChannelService;

@Controller
@RequestMapping(value="/game/operative/cprtchnstat")
public class CooperationChannelStatController {
	@Autowired
	private CooperationChannelService cooperationChannelService;
	
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"game:operative:cprtchnstat"})
	public ModelAndView show(){
		ModelAndView view = new ModelAndView();
		view.setViewName("operative/game/operative/marketcprtchn/cooperation_channel_stat.main");
		return view;
	}
	
	@RequestMapping(value="/list",produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value={"game:operative:cprtchnstat"})
	@ResponseBody
	public String list(int pageNumber,int pageSize,HttpServletRequest request){
		String result = "";
		Map<String, Object> conditionMap = new HashMap<String, Object>();
		conditionMap.put("channelId", request.getParameter("channelId"));
		conditionMap.put("channelName", request.getParameter("channelName"));
		conditionMap.put("dateStart", request.getParameter("dateStart"));
		conditionMap.put("dateEnd", request.getParameter("dateEnd"));
		User  user=(User)request.getSession().getAttribute(SysConstant.SESSION_USER);
		conditionMap.put("userId", user.getUserId()); 
		result = cooperationChannelService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
}
