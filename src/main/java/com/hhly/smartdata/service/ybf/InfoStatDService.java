package com.hhly.smartdata.service.ybf;

import java.util.List;

import com.hhly.smartdata.dto.InfoStatDShower;

import net.sf.json.JSONObject;

public interface InfoStatDService {
    
	/**
	 * 分页获取咨询信息
	 * 
	 * @param domain
	 * @param date
	 * @param infoType
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public JSONObject getInfoStatD(String domain, String date, String infoType, int pageNumber, int pageSize);
	
	/**
	 * 导出所有咨询信息
	 * 
	 * @param domain
	 * @param date
	 * @param infoType
	 * @return
	 */
	public List<InfoStatDShower> getInfoStatDAll(String domain, String date, String infoType);
}
