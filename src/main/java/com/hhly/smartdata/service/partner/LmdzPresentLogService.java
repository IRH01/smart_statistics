package com.hhly.smartdata.service.partner;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.partner.LmdzPresentLog;

import java.util.Map;

public interface LmdzPresentLogService{
    public PageInfo<LmdzPresentLog> find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public String export(Map<String, Object> conditionMap);

    public boolean canExport(Map<String, Object> conditionMap);
}
