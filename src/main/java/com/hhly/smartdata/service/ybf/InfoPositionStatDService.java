package com.hhly.smartdata.service.ybf;

import net.sf.json.JSONObject;

public interface InfoPositionStatDService{

    /**
     * 咨询获取位置信息
     *
     * @param domain
     * @param date
     * @param infoType
     * @param infoId
     * @return
     */
    public JSONObject getInfoPositionStatDByInfo(String domain, String date, String infoId);

    /**
     * 位置获取咨询信息
     *
     * @param domain
     * @param date
     * @param infoType
     * @param infoId
     * @return
     */
    public JSONObject getInfoPositionStatDByPosition(String domain, String date, String positionId);
}
