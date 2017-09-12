package com.hhly.smartdata.service.game;

import net.sf.json.JSONObject;

import java.util.Set;

public interface GameTransDailyService{
    public JSONObject getChart(String platformId, String startDate, String endDate, Set<String> scales);
}
