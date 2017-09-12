package com.hhly.smartdata.service.game;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface GameStatDailyService{
    /**
     * 查找
     *
     * @param conditionMap
     * @return
     */
    public JSONArray find(String platformId, String date);

    /**
     * 统计
     *
     * @param conditionMap
     * @return
     */
    public JSONObject statistics(String platformId, String startDate, String endDate, int pageNumber, int pageSize);
}
