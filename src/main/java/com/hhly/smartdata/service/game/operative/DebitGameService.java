package com.hhly.smartdata.service.game.operative;

import java.util.Map;

import net.sf.json.JSONObject;

public interface DebitGameService {
	public JSONObject find(Map<String, Object> conditionMap,int pageNumber,int pageSize);
	
	public boolean canExport(Map<String, Object> conditionMap);
	
	public String export(Map<String, Object> conditionMap);
}
