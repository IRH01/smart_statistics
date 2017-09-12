package com.hhly.smartdata.service.game.operative;

import net.sf.json.JSONObject;

import java.util.Map;

public interface ViewOrderDetailService{
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public String export(Map<String, Object> conditionMap);

    public boolean canExport(Map<String, Object> conditionMap);

    public JSONObject findQlfMemberData(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public String exportQlfMemberData(Map<String, Object> conditionMap);

    public boolean canExportQlfMemberData(Map<String, Object> conditionMap);
}
