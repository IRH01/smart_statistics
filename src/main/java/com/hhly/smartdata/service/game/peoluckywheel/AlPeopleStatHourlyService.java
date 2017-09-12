package com.hhly.smartdata.service.game.peoluckywheel;

import java.util.Set;

import net.sf.json.JSONObject;

public interface AlPeopleStatHourlyService {
	public JSONObject getChart(String domainId,String channelId,String gameId,String date, Set<String> scales);
}
