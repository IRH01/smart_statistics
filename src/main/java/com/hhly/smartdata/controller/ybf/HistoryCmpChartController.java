package com.hhly.smartdata.controller.ybf;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.StringUtil;
import com.hhly.smartdata.service.ybf.InfoActFactService;
import com.hhly.smartdata.service.ybf.InfoDomainStatDService;
import com.hhly.smartdata.util.DateListUtil;
import com.hhly.smartdata.util.ExportExcellUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/history")
public class HistoryCmpChartController {

	@Resource
	private InfoActFactService infoActFactService;

	@Resource
	private InfoDomainStatDService infoDomainStatDService;

	@RequestMapping(value = "/count")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String historyCmp(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String domain = request.getParameter("domain");

		if (startDate.equals("选择日期") || StringUtil.isEmpty(startDate))
			startDate = null;
		if (endDate.equals("选择日期") || StringUtil.isEmpty(endDate))
			endDate = null;
		if (StringUtil.isEmpty(domain))
			domain = null;

		TreeSet<String> dates = DateListUtil.getLimitCountDateList(startDate, endDate);

		// JSONObject jsonObject = infoActFactService.countHistory(dates);
		JSONObject jsonObject = infoDomainStatDService.countHistory(dates, domain);
		return jsonObject.toString();
	}

	@RequestMapping(value = "/exportToExcel")
	@RequiresPermissions(value = "forecast_manager")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		String domain = request.getParameter("domain");

		if (StringUtil.isEmpty(startDate) || startDate.equals("选择日期"))
			startDate = null;
		if (StringUtil.isEmpty(endDate) || endDate.equals("选择日期"))
			endDate = null;
		if (StringUtil.isEmpty(domain))
			domain = null;

		TreeSet<String> dates = DateListUtil.getLimitCountDateList(startDate, endDate);

		// JSONObject jsonObject = infoActFactService.countHistory(dates);
		JSONObject jsonObject = infoDomainStatDService.countHistory(dates, domain);
		// System.out.println(jsonObject.toString());

		String fileName = startDate + "至" + endDate + "历史对比数据统计";
		String sheetTitle = "历史对比";
		List<String> titleColumns = new ArrayList<String>();
		titleColumns.add(" ");
		JSONArray allDates = jsonObject.getJSONArray("dates");
		for (int i = 0; i < allDates.size(); i++) {
			titleColumns.add(allDates.getString(i));
		}

		List<List<Object>> infos = new ArrayList<List<Object>>();
		List<Object> infoClickNumber = new ArrayList<Object>();
		infoClickNumber.add("点击数量");
		JSONArray allClickNumber = jsonObject.getJSONArray("clickNumberList");
		for (int i = 0; i < allClickNumber.size(); i++) {
			infoClickNumber.add(allClickNumber.getString(i));
		}

		List<Object> infoIpList = new ArrayList<Object>();
		infoIpList.add("ip数量");
		JSONArray allIp = jsonObject.getJSONArray("ipList");
		for (int i = 0; i < allIp.size(); i++) {
			infoIpList.add(allIp.getString(i));
		}

		List<Object> infoConsult = new ArrayList<Object>();
		infoConsult.add("资讯数量");
		JSONArray allConsult = jsonObject.getJSONArray("consultList");
		for (int i = 0; i < allConsult.size(); i++) {
			infoConsult.add(allConsult.getString(i));
		}

		List<Object> infoStayTime = new ArrayList<Object>();
		infoStayTime.add("停留时长（秒）");
		JSONArray allStayTime = jsonObject.getJSONArray("stayTimeList");
		for (int i = 0; i < allStayTime.size(); i++) {
			infoStayTime.add(allStayTime.getString(i));
		}

		infos.add(infoStayTime);
		infos.add(infoClickNumber);
		infos.add(infoConsult);
		infos.add(infoIpList);

		ExportExcellUtil.export(response, fileName, sheetTitle, titleColumns, infos);
	}
}
