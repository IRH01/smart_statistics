package com.hhly.smartdata.service.game.operative.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.operative.GamePlatformDaily;
import com.hhly.smartdata.mapper.game.operative.GamePlatformDailyReposity;
import com.hhly.smartdata.service.game.operative.GamePlatformDailyService;

@Service
public class GamePlatformDailyServiceImpl implements GamePlatformDailyService {

	@Autowired
	private GamePlatformDailyReposity gamePlatformDailyReposity;
	
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<GamePlatformDaily> values = gamePlatformDailyReposity.find(conditionMap);
		PageInfo<GamePlatformDaily> pageInfo = new PageInfo<GamePlatformDaily>(values);
		return JSONObject.fromObject(pageInfo);
	}
}
