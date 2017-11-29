package com.hhly.smartdata.service.interval;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.dto.current.ChartSeriesData;
import com.hhly.smartdata.dto.current.IntervalGameLaunchTimeListReport;
import com.hhly.smartdata.mapper.member.SystemConfigMapper;
import com.hhly.smartdata.mapper.smartdata.IntervalGameLaunchReportMapper;
import com.hhly.smartdata.util.HourListUtil;
import com.hhly.smartdata.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@Service
public class IntervalGameLaunchService{
    protected final static Logger LOGGER = LoggerFactory.getLogger(IntervalGameLaunchService.class);

    @Autowired
    private IntervalGameLaunchReportMapper intervalGameLaunchReportMapper;

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    /**
     * 获取游戏启动次数
     *
     * @param sourceType
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectIntervalGameLaunchTimeListData(String sourceType) throws Exception{
        Map<String, Object> result = Maps.newHashMap();

        //时刻
        TreeSet<String> hourSet = HourListUtil.getHourListPerHour();
        result.put("time", hourSet);

        //平台信息
        String configValue = systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        Map<String, String> platformMap = JsonUtil.jsonStr2Map(configValue);

        Set<Object> listSet = Sets.newLinkedHashSet();
        Map<String, Integer> dataSum = Maps.newHashMap();

        for(String platformCode : platformMap.keySet()){
            //统计数据
            List<IntervalGameLaunchTimeListReport> list = intervalGameLaunchReportMapper.selectIntervalGameLaunchTimeListData(platformCode, sourceType);

            List<IntervalGameLaunchTimeListReport> listNew = Lists.newArrayList();

            for(String timeStr : hourSet){
                boolean isInclude = false; //是否有数据
                for(IntervalGameLaunchTimeListReport item : list){
                    if(timeStr.equals(item.getStatisticTime())){
                        listNew.add(item);
                        isInclude = true;

                        //统计每时刻总额
                        int sum = dataSum.get(timeStr) == null ? 0 : dataSum.get(timeStr);
                        sum += item.getLaunchCount();
                        dataSum.put(timeStr, sum);
                    }
                }

                //如果数据不存在则补全数据
                if(!isInclude){
                    IntervalGameLaunchTimeListReport newIltlr = new IntervalGameLaunchTimeListReport();
                    newIltlr.setLaunchCount(0);
                    newIltlr.setStatisticTime(timeStr);
                    listNew.add(newIltlr);
                }
            }

            Map<String, Object> dataResult = Maps.newHashMap();
            dataResult.put("name", platformMap.get(platformCode));
            dataResult.put("list", listNew);
            listSet.add(dataResult);
        }
        result.put("data", listSet);

        //添加统计总额
        List<IntervalGameLaunchTimeListReport> listSum = Lists.newArrayList();
        for(String timeStr : hourSet){
            int sum = dataSum.get(timeStr) == null ? 0 : dataSum.get(timeStr);
            IntervalGameLaunchTimeListReport intervalGameLaunchTimeListReport = new IntervalGameLaunchTimeListReport();
            intervalGameLaunchTimeListReport.setLaunchCount(sum);
            intervalGameLaunchTimeListReport.setStatisticTime(timeStr);
            listSum.add(intervalGameLaunchTimeListReport);
        }

        result.put("sumData", listSum);

        return result;
    }

    /**
     * 获取游戏启动次数图形数据
     *
     * @param sourceType
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectIntervalGameLaunchChartTimeData(String sourceType) throws Exception{
        Map<String, Object> result = Maps.newHashMap();

        //时刻
        TreeSet<String> hourSet = HourListUtil.getHourListPerHour();
        result.put("time", hourSet);

        //平台信息
        //平台信息
        String configValue = systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        Map<String, String> platformMap = JsonUtil.jsonStr2Map(configValue);

        Set<String> platformSet = new HashSet<>();
        for(Map.Entry<String, String> platform : platformMap.entrySet()){
            platformSet.add(platform.getValue());
        }
        result.put("platform", platformSet);

        //统计数据
        List<ChartSeriesData> seriesData = new ArrayList<>();

        for(String platformCode : platformMap.keySet()){
            ChartSeriesData csData = new ChartSeriesData();
            csData.setName(platformMap.get(platformCode)); //平台名称
            csData.setType("line"); //线条类型

            //统计数据
            List<IntervalGameLaunchTimeListReport> list = intervalGameLaunchReportMapper.selectIntervalGameLaunchTimeListData(platformCode, sourceType);

            int[] dataSum = new int[48];

            int i = 0;
            for(String timeStr : hourSet){
                for(IntervalGameLaunchTimeListReport item : list){
                    if(timeStr.equals(item.getStatisticTime())){
                        dataSum[i] = item.getLaunchCount();
                    }
                }
                i++;
            }
            csData.setData(dataSum);

            seriesData.add(csData);
        }

        result.put("data", seriesData);
        return result;
    }
}
