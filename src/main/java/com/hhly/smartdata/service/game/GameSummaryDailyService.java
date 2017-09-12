package com.hhly.smartdata.service.game;

import java.util.Set;

import net.sf.json.JSONObject;

public interface GameSummaryDailyService {
	public JSONObject find(String platformId,String startDate,String endDate,String deviceTypes, int pageNumber, int pageSize);
	public JSONObject getChart(String platformId,String startDate,String endDate,String deviceTypes, Set<String> scales);
}
