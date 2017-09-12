package com.hhly.smartdata.service.ybf;

import net.sf.json.JSONObject;

import java.util.Set;

public interface InfoPositionStatHService{

    /**
     * 咨询位置每日各个时段统计
     *
     * @param domain
     * @param date
     * @param positionIds
     * @param scales
     * @return
     */
    public JSONObject getInfoPositionStatHCount(String domain, String date, String positionIds, Set<String> scales);

}
