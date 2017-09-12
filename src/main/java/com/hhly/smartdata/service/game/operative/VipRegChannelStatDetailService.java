package com.hhly.smartdata.service.game.operative;

import com.hhly.smartdata.model.game.PlatformInfo;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface VipRegChannelStatDetailService{
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public List<PlatformInfo> getPlatformInfos();
}



