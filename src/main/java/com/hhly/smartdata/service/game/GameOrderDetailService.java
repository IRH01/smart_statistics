package com.hhly.smartdata.service.game;

import com.hhly.smartdata.model.game.operative.GameOrderDetail;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface GameOrderDetailService{
    /**
     * 查找
     *
     * @param conditionMap
     * @return
     */
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public List<GameOrderDetail> getStates(Map<String, Object> condition);

    public List<GameOrderDetail> getProduces(Map<String, Object> condition);

    public boolean canExport(Map<String, Object> conditionMap);

    public String export(Map<String, Object> conditionMap);
}
