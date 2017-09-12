package com.hhly.smartdata.service.game.peoluckywheel.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.peoluckywheel.AlPeopleStatD;
import com.hhly.smartdata.mapper.game.peoluckywheel.AlPeopleStatDReposity;
import com.hhly.smartdata.service.game.peoluckywheel.AlPeopleStatDailyService;

@Service
public class AlPeopleStatDailyServiceImpl implements AlPeopleStatDailyService{
	@Autowired
	private AlPeopleStatDReposity AlPeopleStatDReposity;
	
	public JSONObject find(String domainId,String channelId,String gameId,String startDate,String endDate, int pageNumber, int pageSize){
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("domainId", domainId);
		conditions.put("channelId", channelId);
		conditions.put("startDate", startDate);
		conditions.put("endDate", endDate);
		conditions.put("gameId", gameId);
		
		PageHelper.startPage(pageNumber, pageSize);
		List<AlPeopleStatD> values = AlPeopleStatDReposity.find(conditions);
		PageInfo<AlPeopleStatD> pageInfo = new PageInfo<AlPeopleStatD>(values);
		return JSONObject.fromObject(pageInfo);
	}
	
	
	public JSONObject getChart(String domainId,String channelId,String gameId,String startDate,String endDate, Set<String> scales) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> scaleList = new LinkedList<String>();
		//PV(浏览量)
		List<Long> pvCntList = new LinkedList<Long>();
		//UV(访客数量)
		List<Long> uvCntList = new LinkedList<Long>();
		//平均停留时长
		List<Double> avgStayCntList = new LinkedList<Double>();
		Iterator<String> iterator = scales.iterator();
		
		//查询获取数据
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("domainId", domainId);
		conditions.put("channelId", channelId);
		conditions.put("startDate", startDate);
		conditions.put("endDate", endDate);
		conditions.put("gameId", gameId);
		
		List<AlPeopleStatD> values = AlPeopleStatDReposity.find(conditions);

		while (iterator.hasNext()) {

			long pvCntScale = 0;
			long uvCntScale = 0;
			double avgStayCntScale = 0;
			

			String currentScale = iterator.next();
			for (AlPeopleStatD value : values) {
				if (currentScale.equals(value.getEtlDate())) {
					pvCntScale = value.getPvCnt();
					uvCntScale = value.getUvCnt();
					avgStayCntScale = value.getAvgStayCnt();
				}
			}
			pvCntList.add(pvCntScale);
			uvCntList.add(uvCntScale);
			avgStayCntList.add(avgStayCntScale);
			scaleList.add(currentScale.substring(5));
		}

		result.put("scales", scaleList);
		result.put("pvCntList", pvCntList);
		result.put("uvCntList", uvCntList);
		result.put("avgStayCntList", avgStayCntList);

		return JSONObject.fromObject(result);

	}
}
