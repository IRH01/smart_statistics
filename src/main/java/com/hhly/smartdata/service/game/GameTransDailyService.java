package com.hhly.smartdata.service.game;

import java.util.Set;

import net.sf.json.JSONObject;

public interface GameTransDailyService {
	public JSONObject getChart(String platformId,String startDate,String endDate,Set<String> scales);
}
