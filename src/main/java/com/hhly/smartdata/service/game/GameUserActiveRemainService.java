package com.hhly.smartdata.service.game;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hhly.smartdata.model.game.operative.GameUserActiveRemain;

public interface GameUserActiveRemainService {
	/**
	 * 查找
	 * @param conditionMap
	 * @return
	 */
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);
	
//	public List<GameOrderDetail> getStates(Map<String, Object> condition);
//	
	public List<GameUserActiveRemain> getProduces(Map<String, Object> condition);
	
	public boolean canExport(Map<String, Object> conditionMap);
	
	public String export(Map<String, Object> conditionMap);
}
