package com.hhly.smartdata.mapper.ybf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.ActCountInfo;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.ToolUtil;

@Repository
public class InfoActFactRepository extends BaseRepository {

	/**
	 * 根据日期和域名id查询点击量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 		域名id
	 * @return
	 */
	public long countAllClickByDate(Date startDate,Date endDate,String domainId){
		Long count = 0L;
		Map<String,Object> paramsMap = getConditionMap(startDate,endDate,domainId);
		count = template.selectOne("infoActFact.countAllClickByDate", paramsMap);
		return ToolUtil.convertToLong(count);
	}
	
	/**
	 * 通过日期和域名获取不同类型的资讯的点击量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			域名 Id
	 * @return
	 */
	public List<ActCountInfo> countClickByTypeAdDate(Date startDate,Date endDate,String domainId){
		List<ActCountInfo> value = null;
		Map<String,Object> paramsMap = getConditionMap(startDate,endDate,domainId);
		value = template.selectList("infoActFact.countClickByTypeAdDate", paramsMap);
		return value;
	}
	
	/**
	 * 根据日期和域名id查询IP数量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 		域名id
	 * @return
	 */
	public long countAllIPByDate(Date startDate,Date endDate,String domainId){
		Long count = 0L;
		Map<String,Object> paramsMap = getConditionMap(startDate,endDate,domainId);
		count = template.selectOne("infoActFact.countAllIPByDate", paramsMap);
		return ToolUtil.convertToLong(count);
	}
	
	/**
	 * 通过日期和域名获取不同类型的IP数量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			域名 Id
	 * @return
	 */
	public List<ActCountInfo> countIPByTypeAdDate(Date startDate,Date endDate,String domainId){
		List<ActCountInfo> value = null;
		Map<String,Object> paramsMap = getConditionMap(startDate,endDate,domainId);
		value = template.selectList("infoActFact.countIPByTypeAdDate", paramsMap);
		return value;
	}
	
	/**
	 * 根据日期和域名id查询停留时长
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 		域名id
	 * @return
	 */
	public long countAllStayTimeByDate(Date startDate,Date endDate,String domainId){
		Long count = 0L;
		Map<String,Object> paramsMap = getConditionMap(startDate,endDate,domainId);
		count = template.selectOne("infoActFact.countAllStayTimeByDate", paramsMap);
		return ToolUtil.convertToLong(count);
	}
	
	/**
	 * 通过日期和域名获取不同类型的停留时长
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			域名 Id
	 * @return
	 */
	public List<ActCountInfo> countStayTimeByTypeAdDate(Date startDate,Date endDate,String domainId){
		List<ActCountInfo> value = null;
		Map<String,Object> paramsMap = getConditionMap(startDate,endDate,domainId);
		value = template.selectList("infoActFact.countStayTimeByTypeAdDate", paramsMap);
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
			endDate = DateUtil.getTime(endDate, 23, 59, 59);
			//endDate = DateUtil.add(endDate, Calendar.DAY_OF_MONTH, 1);
			paramsMap.put("endDate",DateUtil.formatDate(endDate, "yyyy-MM-dd HH:mm:ss"));
		}else{
			paramsMap.put("endDate", endDate);
		}
		paramsMap.put("domainId", domainId);
		return paramsMap;
	}
}
