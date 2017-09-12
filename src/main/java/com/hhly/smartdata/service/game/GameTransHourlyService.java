package com.hhly.smartdata.service.game;

import java.util.Set;

import net.sf.json.JSONObject;

public interface GameTransHourlyService {
	public JSONObject getChart(String platformId,String date,Set<String> scales);
}
