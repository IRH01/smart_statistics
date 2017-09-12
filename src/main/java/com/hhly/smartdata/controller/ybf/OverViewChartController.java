package com.hhly.smartdata.controller.ybf;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hhly.smartdata.service.ybf.DimInfoService;
import com.hhly.smartdata.service.ybf.DimPositionService;
import com.hhly.smartdata.service.ybf.InfoActFactService;
import com.hhly.smartdata.util.DateUtil;

@Controller
@RequestMapping(value = "/overviewchart")
public class OverViewChartController {
	@Autowired
	DimInfoService dimInfoService;
	@Autowired
	InfoActFactService infoActFactService;
	@Autowired
	DimPositionService locationService;
	
	/**
	 * 日更新资讯数量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * @return
	 */
	@RequestMapping(value = "/dailyInfoCount",method = RequestMethod.POST)
	@RequiresPermissions(value="forecast_manager")
	@ResponseBody
	public Object getDailyInfoCount(String startDate,String endDate,String domainId){
		Map<String, Object> dataMap = new HashMap<String,Object>();
		dataMap.put("data", infoActFactService.countInfoCnt(DateUtil.parseUtilDate(startDate, "yyyy-MM-dd"), DateUtil.parseUtilDate(endDate, "yyyy-MM-dd"),domainId));
		return dataMap;
	}

	/**
	 * 点击量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * @return
	 */
	@RequestMapping(value = "/clickCount",method = RequestMethod.POST)
	@RequiresPermissions(value="forecast_manager")
	@ResponseBody
	public Object getClickCount(String startDate,String endDate,String domainId){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", infoActFactService.countClickInfo(DateUtil.parseUtilDate(startDate, "yyyy-MM-dd"), DateUtil.parseUtilDate(endDate, "yyyy-MM-dd"),domainId));
		return dataMap;
	}
	
	/**
	 * 导出点击量数据为excel
	 * @param response
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 */
	@RequestMapping(value = "/exportClickCount")
	@RequiresPermissions(value="forecast_manager")
	public void exportClickCount(HttpServletResponse response,String startDate,String endDate,String domainId){
		infoActFactService.exportClickCount(response,DateUtil.parseUtilDate(startDate, "yyyy-MM-dd"), DateUtil.parseUtilDate(endDate, "yyyy-MM-dd"),domainId);
	}
	
	/**
	 * IP数量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * @return
	 */
	@RequestMapping(value = "/ipCount",method = RequestMethod.POST)
	@RequiresPermissions(value="forecast_manager")
	@ResponseBody
	public Object getIPCount(String startDate,String endDate,String domainId){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", infoActFactService.countIPInfo(DateUtil.parseUtilDate(startDate, "yyyy-MM-dd"), DateUtil.parseUtilDate(endDate, "yyyy-MM-dd"),domainId));
		return dataMap;
	}
	
	/**
	 * 导出IP数量数据为excel
	 * @param response
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 */
	@RequestMapping(value = "/exportIPCount")
	@RequiresPermissions(value="forecast_manager")
	public void exportIPCount(HttpServletResponse response,String startDate,String endDate,String domainId){
		infoActFactService.exportIPCount(response,DateUtil.parseUtilDate(startDate, "yyyy-MM-dd"), DateUtil.parseUtilDate(endDate, "yyyy-MM-dd"),domainId);
	}
	
	/**
	 * 停留时长
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * @return
	 */
	@RequestMapping(value = "/stayTime",method = RequestMethod.POST)
	@RequiresPermissions(value="forecast_manager")
	@ResponseBody
	public Object getStayTime(String startDate,String endDate,String domainId){
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", infoActFactService.countStayTimeInfo(DateUtil.parseUtilDate(startDate, "yyyy-MM-dd"), DateUtil.parseUtilDate(endDate, "yyyy-MM-dd"),domainId));
		return dataMap;
	}
	
	/**
	 * 导出数据为excel
	 * @param response
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 */
	@RequestMapping(value = "/exportStayTime")
	@RequiresPermissions(value="forecast_manager")
	public void exportStayTime(HttpServletResponse response,String startDate,String endDate,String domainId){
		infoActFactService.exportStayTimeInfo(response,DateUtil.parseUtilDate(startDate, "yyyy-MM-dd"), DateUtil.parseUtilDate(endDate, "yyyy-MM-dd"),domainId);
	}
	
	@RequestMapping(value = "/exportToExcel")
	@RequiresPermissions(value="forecast_manager")
	public void exportToExcel(HttpServletResponse response,String startDate,String endDate,String domainId){
		infoActFactService.exportToExcel(response,DateUtil.parseUtilDate(startDate, "yyyy-MM-dd"), DateUtil.parseUtilDate(endDate, "yyyy-MM-dd"),domainId);
	}
}
