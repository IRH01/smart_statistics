package com.hhly.smartdata.service.partner;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.partner.LmdzBettingLog;

public interface LmdzBettingLogService {
	public PageInfo<LmdzBettingLog> find(Map<String, Object> conditionMap,int pageNumber,int pageSize);
	
	public String export(Map<String, Object> conditionMap);
	
	public boolean canExport(Map<String, Object> conditionMap);
}
