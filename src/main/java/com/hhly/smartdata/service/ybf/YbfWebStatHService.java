package com.hhly.smartdata.service.ybf;

import java.util.Set;

import net.sf.json.JSONObject;

public interface YbfWebStatHService {

	/**
	 * 列表显示一个各个时间段的信息
	 * 
	 * @param scales
	 * @param domain
	 * @param date
	 * @return
	 */
	public JSONObject getYbfWebStatHList(String domain, String date, int pageNumber, int pageSize);

	/**
	 * 图标显示一天各个时间段的
	 * 
	 * @param domain
	 * @param date
	 * @param scales
	 * @return
	 */
	public JSONObject getYbfWebStatHChart(String date, String urlId,Set<String> scales);
}
