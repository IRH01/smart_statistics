package com.hhly.smartdata.service.ybf;

import java.util.List;

import com.hhly.smartdata.dto.PositionStatDShower;

import net.sf.json.JSONObject;

public interface PositionStatDService {

	/**
	 * 获取每天所有的位置信息
	 * 
	 * @param domain
	 * @param date
	 * @param infoType
	 * @param PageNumber
	 * @param pageSize
	 * @return
	 */
	public JSONObject getPositionStatD(String domain, String date, String positionIds, int pageNumber, int pageSize);

	/**
	 * 
	 * @param domain
	 * @param date
	 * @param positionIds
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public List<PositionStatDShower> getPositionStatDAll(String domain, String date, String positionIds);

	/**
	 * 获取一天的统计
	 * 
	 * @param domain
	 * @param date
	 * @param positionId
	 * @return
	 */
	public JSONObject getPositionStatDCountByDay(String domain, String date, String positionIds);

}
