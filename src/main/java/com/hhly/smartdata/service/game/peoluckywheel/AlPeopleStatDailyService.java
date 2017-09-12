package com.hhly.smartdata.service.game.peoluckywheel;

import java.util.Set;

import net.sf.json.JSONObject;

public interface AlPeopleStatDailyService {
	public JSONObject find(String domainId,String channelId,String gameId,String startDate,String endDate, int pageNumber, int pageSize);
	public JSONObject getChart(String domainId,String channelId,String gameId,String startDate,String endDate, Set<String> scales);
}
