package com.hhly.smartdata.service.game.operative;

import java.util.Map;

import net.sf.json.JSONObject;

public interface GamePlatformDailyService {
	public JSONObject find(Map<String, Object> conditionMap,int pageNumber, int pageSize);
}
