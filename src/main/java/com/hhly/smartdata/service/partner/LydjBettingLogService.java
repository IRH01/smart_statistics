package com.hhly.smartdata.service.partner;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.partner.LydjBettingLog;

import java.util.Map;

public interface LydjBettingLogService{
    public PageInfo<LydjBettingLog> find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public String exportBettingData(Map<String, Object> conditionMap);

    public String exportLosingData(Map<String, Object> conditionMap);

    public boolean canExport(Map<String, Object> conditionMap);
}
