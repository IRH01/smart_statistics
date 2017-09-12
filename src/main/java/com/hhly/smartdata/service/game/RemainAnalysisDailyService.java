package com.hhly.smartdata.service.game;

import net.sf.json.JSONObject;

public interface RemainAnalysisDailyService {
	public JSONObject find(String platformId,String startDate,String endDate,int pageNumber, int pageSize);
}
