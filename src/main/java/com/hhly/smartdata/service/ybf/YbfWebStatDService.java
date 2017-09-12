package com.hhly.smartdata.service.ybf;

import java.util.Set;

import net.sf.json.JSONObject;

public interface YbfWebStatDService {

	/**
	 * 获取列表记录
	 * 
	 * @param dates
	 * @param domain
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public JSONObject getYbfWebStatDList(String domain, String startDate, String endDate, int pageNumber, int pageSize);

	/**
	 * 获取图标记录
	 * 
	 * @param startDate
	 * @param endDate
	 * @param dates
	 * @return
	 */
	public JSONObject getYbfWebStatDChart(String startDate, String endDate, String urlId,Set<String> dates);
}
