package com.hhly.smartdata.service.ybf.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.dto.YbfWebStatD;
import com.hhly.smartdata.mapper.ybf.YbfWebStatDRepository;
import com.hhly.smartdata.service.ybf.YbfWebStatDService;

import net.sf.json.JSONObject;

@Service
public class YbfWebStatDServiceImpl implements YbfWebStatDService {

	@Autowired
	private YbfWebStatDRepository ybfWebStatDRepository;

	@Override
	public JSONObject getYbfWebStatDList(String domain, String startDate, String endDate, int pageNumber,
			int pageSize) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain", domain);
		param.put("startDate", startDate);
		param.put("endDate", endDate);

		PageHelper.startPage(pageNumber, pageSize);
		List<YbfWebStatD> ybfWebStatDs = this.ybfWebStatDRepository.countYbfWebStatD(param);
		PageInfo<YbfWebStatD> pageInfo = new PageInfo<YbfWebStatD>(ybfWebStatDs);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("ybfWebStatDs", pageInfo);
		resultMap.put("pageNumber", pageNumber);

		return JSONObject.fromObject(resultMap);
	}

	@Override
	public JSONObject getYbfWebStatDChart(String startDate, String endDate, String urlId,Set<String> dates) {

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("urlId", urlId);

		List<YbfWebStatD> ybfWebStatDs = this.ybfWebStatDRepository.findYbfWebStatD(param);

		List<String> scaleList = new LinkedList<String>();
		// 停留时长
		List<Long> stayList = new LinkedList<Long>();
		// 点击次数
		List<Long> clickList = new LinkedList<Long>();
		// ip
		List<Long> ipList = new LinkedList<Long>();

		List<Long> viewList = new LinkedList<Long>();

		List<Long> userList = new LinkedList<Long>();

		Iterator<String> iterator = dates.iterator();

		while (iterator.hasNext()) {

			long ipPerDate = 0;
			long stayPerDate = 0;
			long clickPerDate = 0;
			long userPerDate = 0;
			long viewCnt = 0;

			String currentDate = iterator.next();
			for (YbfWebStatD ybfWebStatD : ybfWebStatDs) {

				if (currentDate.equals(ybfWebStatD.getEtlDate())) {
					ipPerDate = ybfWebStatD.getIpCnt();
					stayPerDate = ybfWebStatD.getStayCnt();
					clickPerDate = ybfWebStatD.getClickCnt();
					userPerDate = ybfWebStatD.getUserCnt();
					viewCnt = ybfWebStatD.getViewCnt();
				}
			}

			ipList.add(ipPerDate);
			stayList.add(stayPerDate);
			clickList.add(clickPerDate);
			userList.add(userPerDate);
			viewList.add(viewCnt);
			scaleList.add(currentDate.substring(5));
		}

		result.put("scales", scaleList);
		result.put("ipList", ipList);
		result.put("clickList", clickList);
		result.put("stayList", stayList);
		result.put("userList", userList);
		result.put("viewList", viewList);

		return JSONObject.fromObject(result);
	}
}
