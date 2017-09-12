package com.hhly.smartdata.mapper.ybf;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.DimInfoCount;
import com.hhly.smartdata.util.DateUtil;

@Repository
public class DimInfoRepository extends BaseRepository{
	
	/**
	 * 根据日期查询资讯数量
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int countAllByDate(Date startDate,Date endDate,String domainId){
		int count = 0;
		Map<String,Object> paramsMap = getConditionMap(startDate,endDate,domainId);
		count = template.selectOne("dimInfo.countAllByDate", paramsMap);
		return count;
	}
	
	/**
	 * 通过日期计算不同类型的资讯数量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			要查询的域名 Id
	 * @return
	 */
	public List<DimInfoCount> countTypeByDate(Date startDate,Date endDate,String domainId){
		List<DimInfoCount> value = null;
		Map<String,Object> paramsMap = getConditionMap(startDate,endDate,domainId);
		value = template.selectList("dimInfo.countTypeByDate", paramsMap);
		return value;
	}
	
	/**
	 * 构造查询条件
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * @return
	 */
	private Map<String, Object> getConditionMap(Date startDate,Date endDate,String domainId){
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		if(null != startDate){
			startDate = DateUtil.getTime(startDate, 0, 0, 0);
			paramsMap.put("startDate",DateUtil.formatDate(startDate, "yyyy-MM-dd HH:mm:ss"));
		}else{
			paramsMap.put("startDate", null);
		}
		if(null != endDate){
			endDate = DateUtil.getTime(endDate, 0, 0, 0);
			endDate = DateUtil.add(endDate, Calendar.DAY_OF_MONTH, 1);
			paramsMap.put("endDate",DateUtil.formatDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		}else{
			paramsMap.put("endDate", endDate);
		}
		paramsMap.put("domainId", domainId);
		return paramsMap;
	}
}
