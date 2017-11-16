package com.hhly.smartdata.service.month;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.mapper.member.SystemConfigMapper;
import com.hhly.smartdata.mapper.smartdata.MonthLoginReportMapper;
import com.hhly.smartdata.model.smartdata.MonthLoginReport;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Iritchie.ren on 2017/10/10.
 */
@Service
public class MonthLoginStatisticsService{

    @Autowired
    private MonthLoginReportMapper monthLoginReportMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public Map<String, List> search(String year) throws Exception{
        if(StringUtils.isEmpty(year)){
            year = DateUtil.date2String(new Date(), "yyyy");
        }
        String yearFirstMonthStr = DateUtil.getYearFirstMonthStr(year);
        String yearEndMonthStr = DateUtil.getYearEndMonthStr(year);
        //平台信息
        String configValue = systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        Map<String, String> platformMap = JsonUtil.jsonStr2Map(configValue);
        List<MonthLoginReport> monthCompositeReportResultList = monthLoginReportMapper.searchByTime(yearFirstMonthStr, yearEndMonthStr);
        //获取当年所有月份
        Set<String> statisticsDaySet = Sets.newLinkedHashSet();
        for(MonthLoginReport item : monthCompositeReportResultList){
            if(!statisticsDaySet.contains(item.getStatisticsMonth())){
                statisticsDaySet.add(item.getStatisticsMonth());
            }
        }

        List<Map<String, Object>> list = Lists.newArrayList();
        //每行数据拼接，同一个时间为一行
        for(String dateStr : statisticsDaySet){
            List<Object> dataList = Lists.newArrayList();
            for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
                //综合
                for(MonthLoginReport monthLoginReport : monthCompositeReportResultList){
                    if(monthLoginReport.getStatisticsMonth().equals(dateStr)
                            && monthLoginReport.getSourceType().equals(sourceTypeEnum.getCode())
                            && monthLoginReport.getPlatformCode().equals("zong_he")){
                        dataList.add(monthLoginReport.getLoginPopulation());
                        dataList.add(monthLoginReport.getPlayPopulation());
                        break;
                    }
                }
                //各个平台的
                for(String platformCode : platformMap.keySet()){
                    for(MonthLoginReport monthLoginReport : monthCompositeReportResultList){
                        if(monthLoginReport.getStatisticsMonth().equals(dateStr)
                                && monthLoginReport.getSourceType().equals(sourceTypeEnum.getCode())
                                && monthLoginReport.getPlatformCode().equals(platformCode)){
                            dataList.add(monthLoginReport.getPlayPopulation());
                            break;
                        }
                    }
                }
            }
            Map<String, Object> trData = Maps.newHashMap();
            trData.put("time", dateStr);
            trData.put("data", dataList);
            list.add(trData);
        }

        //title拼接
        List<String> title = Lists.newArrayList();
        for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
            title.add(sourceTypeEnum.getDesc() + "登录人数");
            title.add(sourceTypeEnum.getDesc() + "玩游戏人数");
            for(String platformCode : platformMap.keySet()){
                title.add(sourceTypeEnum.getDesc() + "-" + platformMap.get(platformCode));
            }
        }

        Map<String, List> resultMap = Maps.newHashMap();
        resultMap.put("title", title);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> getLastTotalRegister() throws Exception{
        Date now = new Date();
        String month = DateUtil.getLastMonthStr(now);
        return this.monthLoginReportMapper.selectByMonth(month);
    }
}
