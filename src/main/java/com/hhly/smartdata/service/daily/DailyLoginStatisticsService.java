package com.hhly.smartdata.service.daily;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.dto.enume.SourceTypeEnum;
import com.hhly.smartdata.mapper.member.SystemConfigMapper;
import com.hhly.smartdata.mapper.smartdata.DailyLoginReportMapper;
import com.hhly.smartdata.model.smartdata.DailyLoginReport;
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
 * Created by Iritchie.ren on 2017/10/17.
 */
@Service
public class DailyLoginStatisticsService{

    @Autowired
    private DailyLoginReportMapper dailyLoginReportMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public Map<String, Object> getLastTotalRegister() throws Exception{
        Date now = new Date();
        String yesterdayStr = DateUtil.getYesterdayStr(now);
        return this.dailyLoginReportMapper.selectByDaily(yesterdayStr);
    }

    /**
     * 根据查询条件统计登录来源
     *
     * @param monthOfYear
     * @return
     * @throws Exception
     */
    public Map<String, List> search(String monthOfYear) throws Exception{
        if(StringUtils.isEmpty(monthOfYear)){
            monthOfYear = DateUtil.date2String(new Date(), "yyyy-MM-dd");
        }
        String monthFirstDayStr = DateUtil.getMonthFirstDayStr(monthOfYear);
        String monthEndDayStr = DateUtil.getMonthEndDayStr(monthOfYear);

        //平台信息
        String configValue = systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        Map<String, String> platformMap = JsonUtil.jsonStr2Map(configValue);
        //获取条件范围的所有日期
        List<DailyLoginReport> dailyLoginReportList = dailyLoginReportMapper.searchByTime(monthFirstDayStr, monthEndDayStr);
        //获取当年所有日期
        Set<String> statisticsDaySet = Sets.newLinkedHashSet();
        for(DailyLoginReport item : dailyLoginReportList){
            if(!statisticsDaySet.contains(item.getStatisticsDay())){
                statisticsDaySet.add(item.getStatisticsDay());
            }
        }

        List<Map<String, Object>> list = Lists.newArrayList();
        //每行数据拼接，同一个时间为一行
        for(String dateStr : statisticsDaySet){
            List<Object> dataList = Lists.newArrayList();
            for(SourceTypeEnum sourceTypeEnum : SourceTypeEnum.values()){
                //综合
                for(DailyLoginReport dailyLoginReport : dailyLoginReportList){
                    if(dailyLoginReport.getStatisticsDay().equals(dateStr) && dailyLoginReport.getSourceType().equals(sourceTypeEnum.getCode()) && dailyLoginReport.getPlatformCode().equals("zong_he")){
                        dataList.add(dailyLoginReport.getLoginPopulation());
                        dataList.add(dailyLoginReport.getPlayPopulation());
                        break;
                    }
                }

                //各个平台的
                for(String platformCode : platformMap.keySet()){
                    for(DailyLoginReport dailyLoginReport : dailyLoginReportList){
                        if(dailyLoginReport.getStatisticsDay().equals(dateStr)
                                && dailyLoginReport.getSourceType().equals(sourceTypeEnum.getCode())
                                && dailyLoginReport.getPlatformCode().equals(platformCode)){
                            dataList.add(dailyLoginReport.getPlayPopulation());
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

}
