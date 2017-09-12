package com.hhly.smartdata.service.game;

import net.sf.json.JSONObject;

import java.util.Set;

public interface GameTransHourlyService{
    public JSONObject getChart(String platformId, String date, Set<String> scales);
}
