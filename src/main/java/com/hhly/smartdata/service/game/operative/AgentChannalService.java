package com.hhly.smartdata.service.game.operative;

import net.sf.json.JSONObject;

import java.util.Map;

public interface AgentChannalService{
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public JSONObject findChannelDetails(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public boolean canExport(Map<String, Object> conditionMap);

    public String export(Map<String, Object> conditionMap);


    public JSONObject findChannelCostDetails(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public boolean canExportCost(Map<String, Object> conditionMap);

    public String exportCost(Map<String, Object> conditionMap);


    public JSONObject findChannelRegistDetails(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public boolean canExportRegist(Map<String, Object> conditionMap);

    public String exportRegist(Map<String, Object> conditionMap);

}
