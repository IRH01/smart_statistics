package com.hhly.smartdata.service.game.operative;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hhly.smartdata.model.game.PlatformInfo;

public interface VipRegChannelStatDetailService {
	public JSONObject find(Map<String, Object> conditionMap,int pageNumber,int pageSize);
	
	public List<PlatformInfo> getPlatformInfos();
}



