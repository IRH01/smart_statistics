package com.hhly.smartdata.service.game.impl;

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
import com.hhly.smartdata.model.game.PayLossDaily;
import com.hhly.smartdata.mapper.game.PayLossDReposity;
import com.hhly.smartdata.service.game.PayLossDailyService;

@Service
public class PayLossDailyServiceImpl implements PayLossDailyService{
	@Autowired
	PayLossDReposity payLossDReposity;
	
	public JSONObject find(String platformId,String startDate,String endDate, int pageNumber, int pageSize){
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("platformId", platformId);
		conditions.put("startDate", startDate);
		conditions.put("endDate", endDate);
		PageHelper.startPage(pageNumber, pageSize);
		List<PayLossDaily> values = payLossDReposity.find(conditions);
		PageInfo<PayLossDaily> pageInfo = new PageInfo<PayLossDaily>(values);
		return JSONObject.fromObject(pageInfo);
	}
	
	public JSONObject getChart(String platformId, String startDate,
			String endDate,Set<String> scales) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> scaleList = new LinkedList<String>();
		
		//7日流失率
		List<Double> sevenLossPercentList = new LinkedList<Double>();
		//14日流失率
		List<Double> fourteenLossPercentList = new LinkedList<Double>();
		//30日流失率
		List<Double> thirtyLossPercentList = new LinkedList<Double>();
		
		Iterator<String> iterator = scales.iterator();
		
		//查询获取数据
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("platformId", platformId);
		conditions.put("startDate", startDate);
		conditions.put("endDate", endDate);
		List<PayLossDaily> values = payLossDReposity.find(conditions);

		while (iterator.hasNext()) {

			double sevenLossPercentScale = 0;
			double fourteenLossPercentScale = 0;
			double thirtyLossPercentScale = 0;

			String currentScale = iterator.next();
			for (PayLossDaily value : values) {
				if (currentScale.equals(value.getStatDate())) {
					sevenLossPercentScale = value.getSevenPercent();
					fourteenLossPercentScale = value.getFourteenPercent();
					thirtyLossPercentScale = value.getThirtyPercent();
					
				}
			}
			sevenLossPercentList.add(sevenLossPercentScale);
			fourteenLossPercentList.add(fourteenLossPercentScale);
			thirtyLossPercentList.add(thirtyLossPercentScale);
			
			scaleList.add(currentScale.substring(5));
		}
		result.put("scales", scaleList);
		result.put("sevenLossPercentList", sevenLossPercentList);
		result.put("fourteenLossPercentList", fourteenLossPercentList);
		result.put("thirtyLossPercentList", thirtyLossPercentList);
		return JSONObject.fromObject(result);
	}
}
