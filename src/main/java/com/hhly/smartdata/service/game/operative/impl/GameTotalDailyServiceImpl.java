package com.hhly.smartdata.service.game.operative.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.operative.GameTotalDaily;
import com.hhly.smartdata.mapper.game.operative.GameTotalDailyReposity;
import com.hhly.smartdata.service.game.operative.GameTotalDailyService;

@Service
public class GameTotalDailyServiceImpl implements GameTotalDailyService {

	@Autowired
	private GameTotalDailyReposity gameTotalDailyReposity;
	
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<GameTotalDaily> values = gameTotalDailyReposity.find(conditionMap);
		PageInfo<GameTotalDaily> pageInfo = new PageInfo<GameTotalDaily>(values);
		return JSONObject.fromObject(pageInfo);
	}

}
