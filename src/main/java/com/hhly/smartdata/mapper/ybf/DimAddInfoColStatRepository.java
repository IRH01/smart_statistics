package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.ActCountInfo;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.ToolUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DimAddInfoColStatRepository extends BaseRepository{

    /**
     * 获取总的日更新资讯数量
     *
     * @param startDate
     * @param endDate
     * @param domainId
     * @return
     */
    public long countAllInfoCnt(Date startDate, Date endDate, String domainId){
        Long count = 0L;
        Map<String, Object> paramsMap = getConditionMap(startDate, endDate, domainId);
        count = template.selectOne("dimAddInfoColStat.countAllInfoCnt", paramsMap);
        return ToolUtil.convertToLong(count);
    }

    /**
     * 获取日更新资讯数量信息
     *
     * @param startDate
     * @param endDate
     * @param domainId
     * @return
     */
    public List<ActCountInfo> countTypeInfoCnt(Date startDate, Date endDate, String domainId){
        List<ActCountInfo> value = null;
        Map<String, Object> paramsMap = getConditionMap(startDate, endDate, domainId);
        value = template.selectList("dimAddInfoColStat.countTypeInfoCnt", paramsMap);
        return value;
    }


    /**
     * 构造查询条件
     *
     * @param startDate
     * @param endDate
     * @param domainId
     * @return
     */
    private Map<String, Object> getConditionMap(Date startDate, Date endDate, String domainId){
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        if(null != startDate){
            startDate = DateUtil.getTime(startDate, 0, 0, 0);
            paramsMap.put("startDate", DateUtil.formatDate(startDate, "yyyy-MM-dd HH:mm:ss"));
        }else{
            paramsMap.put("startDate", null);
        }
        if(null != endDate){
            endDate = DateUtil.getTime(endDate, 23, 59, 59);
            //endDate = DateUtil.add(endDate, Calendar.DAY_OF_MONTH, 1);
            paramsMap.put("endDate", DateUtil.formatDate(endDate, "yyyy-MM-dd HH:mm:ss"));
        }else{
            paramsMap.put("endDate", endDate);
        }
        paramsMap.put("domainId", domainId);
        return paramsMap;
    }
}
