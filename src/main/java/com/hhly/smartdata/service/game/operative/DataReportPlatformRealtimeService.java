package com.hhly.smartdata.service.game.operative;

import net.sf.json.JSONObject;

import java.util.Map;

public interface DataReportPlatformRealtimeService{

    public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

}
