package com.hhly.smartdata.controller.ybf;

import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.service.ybf.DimYbfUrlCfgService;
import com.hhly.smartdata.service.ybf.YbfWebStatDService;
import com.hhly.smartdata.service.ybf.YbfWebStatHService;
import com.hhly.smartdata.util.DateListUtil;
import com.hhly.smartdata.util.HourListUtil;

@Controller
@RequestMapping(value = "/ybfwebdata")
public class YbfFootballController {

	@Autowired
	private YbfWebStatDService ybfWebStatDService;

	@Autowired
	private YbfWebStatHService ybfWebStatHService;
	
	@Autowired
	private DimYbfUrlCfgService dimYbfUrlCfgService;

	
	@RequestMapping(value="/show")
	@RequiresPermissions(value = "ybfwebdataview")
	public ModelAndView show(ModelMap modelMap){
		modelMap.put("domains", dimYbfUrlCfgService.getDomains());
		return new ModelAndView("operative/ybf/ybf_web_data.main");
	}
	
	@RequestMapping(value="/getUrlNames")
	@RequiresPermissions(value = "ybfwebdataview")
	@ResponseBody
	public Object getUrlNames(String domainName){
		return this.dimYbfUrlCfgService.getUrlCfgByDomain(domainName);
	}
	
	/**
	 * 
	 * 一比分足球表统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list", produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value = "ybfwebdataview")
	@ResponseBody
	public String getList(HttpServletRequest request, HttpServletResponse response) {

		String domain = request.getParameter("domain");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");

		return ybfWebStatDService.getYbfWebStatDList(domain, startDate, endDate, Integer.valueOf(pageNumber),
				Integer.valueOf(pageSize)).toString();

	}

	/**
	 * 以比分足球图统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/chart", produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value = "ybfwebdataview")
	@ResponseBody
	public String getChart(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String urlId = request.getParameter("urlId");

		if (startDate != null && endDate != null) {
			if(startDate.equalsIgnoreCase(endDate)){
				TreeSet<String> scales = HourListUtil.getHourListPerHour();
				return ybfWebStatHService.getYbfWebStatHChart(startDate,urlId, scales).toString();
			}else{
				TreeSet<String> dates = DateListUtil.getCountDateList(startDate, endDate);
				return ybfWebStatDService.getYbfWebStatDChart(startDate, endDate, urlId,dates).toString();
			}
		}
		return null;
	}
}
