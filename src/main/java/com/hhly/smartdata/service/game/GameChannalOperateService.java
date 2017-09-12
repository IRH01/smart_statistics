package com.hhly.smartdata.service.game;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hhly.smartdata.model.game.operative.GameChannalOperate;

public interface GameChannalOperateService {
	
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);
	
	public List<GameChannalOperate> getProduces(Map<String, Object> condition);
	
	public boolean canExport(Map<String, Object> conditionMap);
	
	public String export(Map<String, Object> conditionMap);
}
