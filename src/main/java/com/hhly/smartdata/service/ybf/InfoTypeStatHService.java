package com.hhly.smartdata.service.ybf;

import net.sf.json.JSONObject;

import java.util.Set;

public interface InfoTypeStatHService{

    /**
     * 咨询类型每天时刻统计
     *
     * @param domain
     * @param date
     * @param infoType
     * @param scales
     * @return
     */
    public JSONObject getInfoTypeStatHCount(String domain, String date, String infoType, Set<String> scales);

}
