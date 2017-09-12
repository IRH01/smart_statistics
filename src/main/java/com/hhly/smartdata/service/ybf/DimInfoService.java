package com.hhly.smartdata.service.ybf;

import java.util.Date;
import java.util.Map;

public interface DimInfoService {
	
	/**
	 * 根据日期计算，获取图表信息
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			要查询的域名Id
	 * @return
	 */
	public Map<String,Object> count(Date startDate,Date endDate,String domainId);
}
