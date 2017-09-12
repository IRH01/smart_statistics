package com.hhly.smartdata.service.game.operative;

import net.sf.json.JSONObject;

import java.util.Map;

public interface LawyerUserStatisticsService{
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public boolean canExport(Map<String, Object> conditionMap);

    public boolean canExportMonth(Map<String, Object> conditionMap);

    public boolean canExportDay(Map<String, Object> conditionMap);

    public String export(Map<String, Object> conditionMap);

    public String exportMonth(Map<String, Object> conditionMap);

    public String exportDay(Map<String, Object> conditionMap);

    public int count(Map<String, Object> conditionMap);

    public JSONObject findMonth(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public JSONObject findDay(Map<String, Object> conditionMap, int pageNumber, int pageSize);
}
