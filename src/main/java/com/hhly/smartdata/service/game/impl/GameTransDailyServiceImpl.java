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

import com.hhly.smartdata.model.game.GameTransDaily;
import com.hhly.smartdata.mapper.game.GameTransDReposity;
import com.hhly.smartdata.service.game.GameTransDailyService;

@Service
public class GameTransDailyServiceImpl implements GameTransDailyService{
	
	@Autowired
	private GameTransDReposity gameTransDReposity;
	
	@Override
	public JSONObject getChart(String platformId, String startDate,
			String endDate,Set<String> scales) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<String> scaleList = new LinkedList<String>();
		//注册玩家充值次数
		List<Long> regAndRechargeTimesList = new LinkedList<Long>();
		//注册玩家充值人数
		List<Long> regAndRechargeCountList = new LinkedList<Long>();
		//注册玩家充值金额
		List<Long> regAndRechargeAmountList = new LinkedList<Long>();
		
		//活跃玩家充值次数
		List<Long> actAndRechargeTimesList = new LinkedList<Long>();
		//活跃玩家充值人数
		List<Long> actAndRechargeCountList = new LinkedList<Long>();
		//活跃玩家充值金额
		List<Long> actAndRechargeAmountList = new LinkedList<Long>();

		Iterator<String> iterator = scales.iterator();
		
		//查询获取数据
		Map<String, Object> conditions = new HashMap<String, Object>();
		conditions.put("platformId", platformId);
		conditions.put("startDate", startDate);
		conditions.put("endDate", endDate);
		List<GameTransDaily> values = gameTransDReposity.statistics(conditions);

		while (iterator.hasNext()) {

			long regAndRechargeTimesScale = 0;
			long regAndRechargeCountScale = 0;
			long regAndRechargeAmountScale = 0;
			long actAndRechargeTimesScale = 0;
			long actAndRechargeCountScale = 0;
			long actAndRechargeAmountScale = 0;

			String currentScale = iterator.next();
			for (GameTransDaily value : values) {
				if (currentScale.equals(value.getStatDate())) {
					regAndRechargeTimesScale = value.getRegAndRechargeTimes();
					regAndRechargeCountScale = value.getRegAndRechargeCount();
					regAndRechargeAmountScale = value.getRegAndRechargeAmount();
					
					actAndRechargeTimesScale = value.getActAndRechargeTimes();
					actAndRechargeCountScale = value.getActAndRechargeCount();
					actAndRechargeAmountScale = value.getActAndRechargeAmount();
				}
			}
			regAndRechargeTimesList.add(regAndRechargeTimesScale);
			regAndRechargeCountList.add(regAndRechargeCountScale);
			regAndRechargeAmountList.add(regAndRechargeAmountScale);
			
			actAndRechargeTimesList.add(actAndRechargeTimesScale);
			actAndRechargeCountList.add(actAndRechargeCountScale);
			actAndRechargeAmountList.add(actAndRechargeAmountScale);
			
			scaleList.add(currentScale.substring(5));
		}

		result.put("scales", scaleList);
		result.put("regAndRechargeTimesList", regAndRechargeTimesList);
		result.put("regAndRechargeCountList", regAndRechargeCountList);
		result.put("regAndRechargeAmountList", regAndRechargeAmountList);
		
		result.put("actAndRechargeTimesList", actAndRechargeTimesList);
		result.put("actAndRechargeCountList", actAndRechargeCountList);
		result.put("actAndRechargeAmountList", actAndRechargeAmountList);

		return JSONObject.fromObject(result);
	}

}
