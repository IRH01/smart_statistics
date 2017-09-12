package com.hhly.smartdata.service.game.peoluckywheel;

import net.sf.json.JSONObject;

import java.util.Set;

public interface AlPeopleStatHourlyService{
    public JSONObject getChart(String domainId, String channelId, String gameId, String date, Set<String> scales);
}
