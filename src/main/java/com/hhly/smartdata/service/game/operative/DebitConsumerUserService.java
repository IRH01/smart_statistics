package com.hhly.smartdata.service.game.operative;

import net.sf.json.JSONObject;

import java.util.Map;

public interface DebitConsumerUserService{
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public boolean canExport(Map<String, Object> conditionMap);

    public String export(Map<String, Object> conditionMap);

    public JSONObject findCon(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public boolean canExportList(Map<String, Object> conditionMap);

    public String exportList(Map<String, Object> conditionMap);
}
