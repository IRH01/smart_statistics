package com.hhly.smartdata.service.ybf;

import java.util.TreeSet;

import net.sf.json.JSONObject;

public interface InfoDomainStatDService {

	/**
	 * 获取历史记录统计
	 * 
	 * @param dates
	 * @param domain
	 * @return
	 */
	public JSONObject countHistory(TreeSet<String> dates, String domain);

}
