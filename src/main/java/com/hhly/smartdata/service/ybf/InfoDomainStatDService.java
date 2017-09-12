package com.hhly.smartdata.service.ybf;

import net.sf.json.JSONObject;

import java.util.TreeSet;

public interface InfoDomainStatDService{

    /**
     * 获取历史记录统计
     *
     * @param dates
     * @param domain
     * @return
     */
    public JSONObject countHistory(TreeSet<String> dates, String domain);

}
