package com.hhly.smartdata.service.game.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.GameStatDaily;
import com.hhly.smartdata.mapper.game.GameStatDReposity;
import com.hhly.smartdata.service.game.GameStatDailyService;

@Service
public class GameStatDailyServiceImpl implements GameStatDailyService {

	@Autowired
	GameStatDReposity gameStatDReposity;
	
	@Override
	public JSONArray find(String platformId,String date) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("platformId", platformId);
		condition.put("date", date);
		JSONArray jsonArray = JSONArray.fromObject(gameStatDReposity.find(condition));
		return jsonArray;
	}

	@Override
	public JSONObject statistics(String platformId,String startDate,String endDate,int pageNumber, int pageSize) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("platformId", platformId);
		condition.put("startDate", startDate);
		condition.put("endDate", endDate);
		PageHelper.startPage(pageNumber, pageSize);
		List<GameStatDaily> values = gameStatDReposity.statistics(condition);
		PageInfo<GameStatDaily> pageInfo = new PageInfo<GameStatDaily>(values);
		return JSONObject.fromObject(pageInfo);
	}

}
