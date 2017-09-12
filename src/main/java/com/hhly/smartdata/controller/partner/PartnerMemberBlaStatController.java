package com.hhly.smartdata.controller.partner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.service.partner.PartnerMemberBlaStatService;
import com.hhly.smartdata.util.FileUtil;

/**
 * 合格会员佣金报表
 * @author wanghuang
 *
 */
@Controller
@RequestMapping(value="/partner/ptnmbblastat")
public class PartnerMemberBlaStatController {
	
	@Autowired
	private PartnerMemberBlaStatService partnerMemberBlaStatService;
	
	@RequiresPermissions(value={"partner:checkreport"})
	@RequestMapping(value="/show")
	public ModelAndView show(ModelMap modelMap){
		ModelAndView view = new ModelAndView();
		view.setViewName("operative/partner/partner_member_bla_stat.main");
		return view;
	}
	
	@RequiresPermissions(value={"partner:checkreport"})
	@RequestMapping(value="/list",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String list(HttpServletRequest request,int pageNumber,int pageSize){
		String result = "";
		result = JSONObject.fromObject(partnerMemberBlaStatService.find(getConditionMap(request), pageNumber, pageSize)).toString();
		return result;
	}
	
	private Map<String, Object> getConditionMap(HttpServletRequest request){
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("partnerUserId", request.getParameter("partnerUserId"));
		conMap.put("monthStart", request.getParameter("monthStart"));
		conMap.put("monthEnd", request.getParameter("monthEnd"));
		return conMap;
	}
	
	@RequiresPermissions(value={"partner:checkreport"})
	@RequestMapping(value="/export")
	@ResponseBody
	public Object export(HttpServletRequest request,HttpSession session){
		Map<String, Object> conditionMap = this.getConditionMap(request);
		Map<String, Object> result = new HashMap<String, Object>();
		if(!partnerMemberBlaStatService.canExport(conditionMap)){
			result.put("canExport", false);
			return result;
		}
		String filePath = partnerMemberBlaStatService.export(conditionMap);
		String uuid = UUID.randomUUID().toString();
		session.setAttribute(uuid, filePath);
		result.put("canExport", true);
		result.put("uuid", uuid);
		return result;
	}
	
	@RequiresPermissions(value={"partner:checkreport"})
	@RequestMapping(value="/download")
	public void download(String uuid,HttpServletResponse response,HttpSession session){
		String filePath = (String)session.getAttribute(uuid);
		FileUtil.downLoadFile(response, new File(filePath), "合作伙伴对账报表");
		FileUtil.delete(filePath);
	}
}
