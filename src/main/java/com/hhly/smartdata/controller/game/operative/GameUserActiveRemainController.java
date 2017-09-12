package com.hhly.smartdata.controller.game.operative;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.hhly.smartdata.service.game.GameUserActiveRemainService;
import com.hhly.smartdata.util.FileUtil;
import com.hhly.smartdata.util.page.Page;

@Controller
@RequestMapping(value="/game/operative/gameUserActiveRemain")
public class GameUserActiveRemainController {
	@Autowired
	private GameUserActiveRemainService gameUserActiveRemainService;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"game:operative:active:remain"})
	public ModelAndView show(@ModelAttribute Page page) {
        Map<String,Object> model = Maps.newHashMap();
//        model.put("states", gameUserActiveRemainService.getStates(null));
        model.put("produces", gameUserActiveRemainService.getProduces(null));
        return  new ModelAndView("/operative/game/operative/game_user_active_remain.main" , model);
	}
	
	@RequestMapping(value="/list",produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value={"game:operative:active:remain"})
	@ResponseBody
	public String list(int pageNumber,int pageSize,HttpServletRequest request){
		String result = "";
		Map<String, Object> conditionMap = getParam(request);
		result = gameUserActiveRemainService.find(conditionMap, pageNumber, pageSize).toString();
		return result;
	}
	
	public Map<String, Object> getParam(HttpServletRequest request) {
		Map<String, Object> conditionMap = new HashMap<String, Object>();
//		conditionMap.put("paymentDrderno", request.getParameter("paymentDrderno"));
		conditionMap.put("channelId", request.getParameter("channelId"));
//		conditionMap.put("userId1", request.getParameter("userId1"));
		conditionMap.put("dateStart", request.getParameter("dateStart"));
		conditionMap.put("dateEnd", request.getParameter("dateEnd"));
		conditionMap.put("platformId", request.getParameter("platformId"));
//		conditionMap.put("payStatus", request.getParameter("payStatus"));
		User  user = (User)request.getSession().getAttribute(SysConstant.SESSION_USER);
		conditionMap.put("userId", user.getUserId()); 
		return conditionMap;
	}
	
	@RequiresPermissions(value={"game:operative:active:remain"})
	@RequestMapping(value="/export")
	@ResponseBody
	public Object export(HttpServletRequest request,HttpSession session){
		Map<String, Object> conditionMap = getParam(request);
		
		Map<String, Object> result = new HashMap<String, Object>();
		if(!gameUserActiveRemainService.canExport(conditionMap)){
			result.put("canExport", false);
			return result;
		}
		String filePath = gameUserActiveRemainService.export(conditionMap);
		String uuid = UUID.randomUUID().toString();
		session.setAttribute(uuid, filePath);
		result.put("canExport", true);
		result.put("uuid", uuid);
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:active:remain"})
	@RequestMapping(value="/download")
	public void download(String uuid, HttpServletResponse response, HttpSession session){
		String filePath = (String)session.getAttribute(uuid);
		FileUtil.downLoadFile(response, new File(filePath), "用户留存");
		FileUtil.delete(filePath);
	}
}
