package com.hhly.smartdata.service.game;

import com.hhly.smartdata.model.game.operative.GameChannalOperate;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface GameChannalOperateService{

    public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public List<GameChannalOperate> getProduces(Map<String, Object> condition);

    public boolean canExport(Map<String, Object> conditionMap);

    public String export(Map<String, Object> conditionMap);
}
