package com.hhly.smartdata.service.ybf;

import java.util.Set;

import net.sf.json.JSONObject;

public interface InfoTypeStatHService {

	/**
	 * 咨询类型每天时刻统计
	 * 
	 * @param domain
	 * @param date
	 * @param infoType
	 * @param scales
	 * @return
	 */
	public JSONObject getInfoTypeStatHCount(String domain, String date, String infoType, Set<String> scales);

}
