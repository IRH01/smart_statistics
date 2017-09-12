package com.hhly.smartdata.service.game;

import net.sf.json.JSONObject;

import java.util.Set;

public interface GameSummaryDailyService{
    public JSONObject find(String platformId, String startDate, String endDate, String deviceTypes, int pageNumber, int pageSize);

    public JSONObject getChart(String platformId, String startDate, String endDate, String deviceTypes, Set<String> scales);
}
