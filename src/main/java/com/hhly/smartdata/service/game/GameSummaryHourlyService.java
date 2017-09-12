package com.hhly.smartdata.service.game;

import net.sf.json.JSONObject;

import java.util.Set;

public interface GameSummaryHourlyService{
    public JSONObject find(String platformId, String date, String deviceTypes, int pageNumber, int pageSize);

    public JSONObject getChart(String platformId, String date, String deviceTypes, Set<String> scales);
}
