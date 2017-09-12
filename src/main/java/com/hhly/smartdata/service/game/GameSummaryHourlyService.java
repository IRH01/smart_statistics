package com.hhly.smartdata.service.game;

import java.util.Set;

import net.sf.json.JSONObject;

public interface GameSummaryHourlyService {
	public JSONObject find(String platformId,String date,String deviceTypes, int pageNumber, int pageSize);
	public JSONObject getChart(String platformId,String date,String deviceTypes, Set<String> scales);
}
