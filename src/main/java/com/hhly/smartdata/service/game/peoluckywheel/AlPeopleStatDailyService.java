package com.hhly.smartdata.service.game.peoluckywheel;

import net.sf.json.JSONObject;

import java.util.Set;

public interface AlPeopleStatDailyService{
    public JSONObject find(String domainId, String channelId, String gameId, String startDate, String endDate, int pageNumber, int pageSize);

    public JSONObject getChart(String domainId, String channelId, String gameId, String startDate, String endDate, Set<String> scales);
}
