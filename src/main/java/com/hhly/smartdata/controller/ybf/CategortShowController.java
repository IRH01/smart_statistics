package com.hhly.smartdata.controller.ybf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.dto.InfoStatDShower;
import com.hhly.smartdata.dto.InfoTypeDto;
import com.hhly.smartdata.dto.PositionStatDShower;
import com.hhly.smartdata.model.ybf.InfoColStatD;
import com.hhly.smartdata.service.ybf.DimInfoService;
import com.hhly.smartdata.service.ybf.DimInfoTypeService;
import com.hhly.smartdata.service.ybf.DimPositionService;
import com.hhly.smartdata.service.ybf.InfoColStatDService;
import com.hhly.smartdata.service.ybf.InfoPositionStatDService;
import com.hhly.smartdata.service.ybf.InfoPositionStatHService;
import com.hhly.smartdata.service.ybf.InfoStatDService;
import com.hhly.smartdata.service.ybf.InfoTypeStatDService;
import com.hhly.smartdata.service.ybf.InfoTypeStatHService;
import com.hhly.smartdata.service.ybf.PositionStatDService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.HourListUtil;

@Controller
@RequestMapping("/category")
@RequiresPermissions(value = "forecast_manager")
public class CategortShowController {

	@Autowired
	DimPositionService locationService;

	@Autowired
	DimInfoTypeService dimInfoTypeService;

	@Autowired
	DimInfoService dimInfoService;

	@Autowired
	InfoTypeStatDService infoTypeStatDService;

	@Autowired
	InfoTypeStatHService infoTypeStatHService;

	@Autowired
	InfoPositionStatHService infoPositionStatHService;

	@Autowired
	InfoStatDService infoStatDService;

	@Autowired
	PositionStatDService positionStatDService;

	@Autowired
	InfoPositionStatDService infoPositionStatDService;

	@Autowired
	DimPositionService dimPositionService;

	@Autowired
	InfoColStatDService infoColStatDService;

	/**
	 * 跳转到主页
	 * 
	 * @param session
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/show")
	@RequiresPermissions(value = "forecast_manager")
	public ModelAndView list(HttpServletRequest request, HttpSession session, ModelMap modelMap) {
		modelMap.put("domains", locationService.getDomains());
		return new ModelAndView("operative/ybf/category_show.main");
	}

	@RequestMapping(value = "/getInfoType", produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String list(HttpServletRequest request, HttpSession session) {

		String date = request.getParameter("date");
		String domain = request.getParameter("domain");

		Map<String, Object> resultMap = new HashMap<String, Object>();

		List<InfoTypeDto> infoTypeDtos = dimInfoTypeService.getAll(date, domain);
		if(CollectionUtils.isEmpty(infoTypeDtos)){
			infoTypeDtos = new ArrayList<InfoTypeDto>();
			InfoTypeDto item = new InfoTypeDto();
			item.setTblId("");
			item.setTypeName("无数据");
			infoTypeDtos.add(item);
		}else{
			for (InfoTypeDto infoTypeDto : infoTypeDtos) {
				if (infoTypeDto.getTypeId().equals("EMPTY")) {
					infoTypeDto.setTypeName("其他");
				}
			}
			//增加“全部”类别到第一项
			InfoTypeDto item = new InfoTypeDto();
			item.setTblId("");
			item.setTypeName("全部");
			infoTypeDtos.add(0, item);
		}
		resultMap.put("infoTypes", infoTypeDtos);

		return JSONObject.fromObject(resultMap).toString();
	}

	/**
	 * 获取每天资讯信息的总计
	 * 
	 */
	@RequestMapping("/infoPerDayCount")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String getInfoTotalPerDay(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("date");
		String infoType = request.getParameter("infoType");
		String domain = request.getParameter("domain");
		//type 为空的时候，查询的是全部资讯
		if(StringUtils.isEmpty(infoType)||"null".equalsIgnoreCase(infoType)){
			infoType = "-99";
		}
		InfoColStatD infoColStatD = infoColStatDService.getInfoColStatD(infoType, domain, date);
		
		return JSONObject.fromObject(infoColStatD).toString();
	}

	/**
	 * 位置信息统计
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/positionPerDayCount")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String getPositionTotalPerDay(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("date");
		String positionIds = request.getParameter("positionIds");
		String domain = request.getParameter("domain");
		return positionStatDService.getPositionStatDCountByDay(domain, date, positionIds).toString();
	}

	/**
	 * 每天资讯时间分布图
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/temporalDisByInfoType")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String temporalDistributionByInfo(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("date");
		String infoType = request.getParameter("infoType");
		String domain = request.getParameter("domain");
		//type 为空的时候，查询的是全部资讯
		if(StringUtils.isEmpty(infoType)||"null".equalsIgnoreCase(infoType)){
			infoType = "-99";
		}
		Set<String> scales = HourListUtil.getHourListPerHour();
		return infoTypeStatHService.getInfoTypeStatHCount(domain, date, infoType, scales).toString();
	}

	/**
	 * 每天位置资讯分布
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/temporalDisByPosition")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String temporalDistributionByPosition(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("date");
		String positionIds = request.getParameter("positionIds");
		String domain = request.getParameter("domain");
		Set<String> scales = HourListUtil.getHourListPerHour();
		return infoPositionStatHService.getInfoPositionStatHCount(domain, date, positionIds, scales).toString();
	}

	/**
	 * 
	 * 获取资讯列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findInfoStatD", produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String getInfoStatD(HttpServletRequest request, HttpServletResponse response) {
		
		String date = request.getParameter("date");
		String infoType = request.getParameter("infoType");
		String domain = request.getParameter("domain");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		
		return infoStatDService
				.getInfoStatD(domain, date, infoType, Integer.valueOf(pageNumber), Integer.valueOf(pageSize))
				.toString();
	}

	/**
	 * 获取位置列表
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findPositionStatD", produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String getPositionStatD(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("date");
		String positionIds = request.getParameter("positionIds");
		String domain = request.getParameter("domain");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		
		return positionStatDService
				.getPositionStatD(domain, date, positionIds, Integer.valueOf(pageNumber), Integer.valueOf(pageSize))
				.toString();
	}

	/**
	 * 资讯获取位置信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findPositionByInfo", produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String getInfoPositionStatDByInfo(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("date");
		String domain = request.getParameter("domain");
		String infoId = request.getParameter("infoId");
		
		return this.infoPositionStatDService.getInfoPositionStatDByInfo(domain, date, infoId).toString();
	}

	/**
	 * 导出咨询列表到excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportInfoList")
	@RequiresPermissions(value = "forecast_manager")
	public void exportInfoList(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("date");
		String infoType = request.getParameter("infoType");
		String domain = request.getParameter("domain");

		List<InfoStatDShower> infoStatDShowers = infoStatDService.getInfoStatDAll(domain, date, infoType);

		String fileName = date + "资讯信息";
		String sheetName = "资讯信息";

		List<String> titleColumns = new ArrayList<String>();
		titleColumns.add("类别");
		titleColumns.add("标题");
		titleColumns.add("发布时间");
		titleColumns.add("点击次数");
		titleColumns.add("停留时长（秒）");
		titleColumns.add("ip数");

		List<List<Object>> infos = new ArrayList<List<Object>>();
		for (InfoStatDShower infoStatDShower : infoStatDShowers) {

			List<Object> info = new ArrayList<Object>();

			info.add(infoStatDShower.getInfoTypeName());
			info.add(infoStatDShower.getInfoName());
			info.add(infoStatDShower.getCreateDate());
			info.add(infoStatDShower.getClickCnt());
			info.add(infoStatDShower.getStayCnt());
			info.add(infoStatDShower.getIpCnt());

			infos.add(info);
		}

		ExportExcellUtil.export(response, fileName, sheetName, titleColumns, infos);
	}

	/**
	 * 导出位置列表到excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportPositionList")
	@RequiresPermissions(value = "forecast_manager")
	public void exportPositionList(HttpServletRequest request, HttpServletResponse response) {

		String date = request.getParameter("date");
		String positionIds = request.getParameter("positionIds");
		String domain = request.getParameter("domain");

		List<PositionStatDShower> positionStatDShowers = positionStatDService.getPositionStatDAll(domain, date,
				positionIds);

		String fileName = date + "位置信息";
		String sheetName = "位置信息";

		List<String> titleColumns = new ArrayList<String>();
		titleColumns.add("位置");
		titleColumns.add("点击次数");
		titleColumns.add("停留时长（秒）");
		titleColumns.add("ip数");
		titleColumns.add("更新数量");

		List<List<Object>> infos = new ArrayList<List<Object>>();
		for (PositionStatDShower positionStatDShower : positionStatDShowers) {

			List<Object> info = new ArrayList<Object>();

			info.add(positionStatDShower.getPositionName());
			info.add(positionStatDShower.getClickCnt());
			info.add(positionStatDShower.getStayCnt());
			info.add(positionStatDShower.getIpcnt());
			info.add(positionStatDShower.getInfoCnt());

			infos.add(info);
		}

		ExportExcellUtil.export(response, fileName, sheetName, titleColumns, infos);

	}

	/**
	 * 获取位置对应的资讯信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findInfoByPosition", produces = "text/plain;charset=UTF-8")
	@RequiresPermissions(value = "forecast_manager")
	@ResponseBody
	public String getInfoPositionStatDByPosition(HttpServletRequest request, HttpServletResponse response) {
		
		String date = request.getParameter("date");
		String domain = request.getParameter("domain");
		String positionId = request.getParameter("positionId");
		
		return this.infoPositionStatDService.getInfoPositionStatDByPosition(domain, date, positionId).toString();
	}

	@RequiresPermissions(value = "forecast_manager")
	@RequestMapping(value = "getPositions")
	@ResponseBody
	public Map<String, Object> getPositions(String domainId) {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Node> positions = dimPositionService.getPositions(domainId);
		StringBuffer positionIds = new StringBuffer();
		StringBuffer positionNames = new StringBuffer();
		/*if (!CollectionUtils.isEmpty(positions)) {
			// 默认所有的都选中
			boolean isFirst = true;
			Map<String, String> nodeMap = new HashMap<String, String>();
			for (Node node : positions) {
				nodeMap.put(node.getpId(), node.getpId());
			}
			for (Node node : positions) {
				node.setChecked(true);
				if(!nodeMap.containsKey(node.getId())){
					if (isFirst == false) {
						positionIds.append(",");
						positionNames.append(",");
					}
					positionNames.append(node.getName());
					positionIds.append(node.getId());
					isFirst = false;
				}
			}
		}*/
		data.put("positions", positions);
		data.put("positionIds", positionIds.toString());
		data.put("positionNames", positionNames.toString());
		return data;
	}
}
