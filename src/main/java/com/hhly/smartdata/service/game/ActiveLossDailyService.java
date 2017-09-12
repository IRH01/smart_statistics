package com.hhly.smartdata.service.game;

import net.sf.json.JSONObject;

import java.util.Set;

public interface ActiveLossDailyService{
    public JSONObject find(String platformId, String startDate, String endDate, int pageNumber, int pageSize);

    public JSONObject getChart(String platformId, String startDate,
                               String endDate, Set<String> scales);
}
