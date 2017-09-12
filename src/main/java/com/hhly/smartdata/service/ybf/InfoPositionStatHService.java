package com.hhly.smartdata.service.ybf;

import java.util.Set;

import net.sf.json.JSONObject;

public interface InfoPositionStatHService {

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
