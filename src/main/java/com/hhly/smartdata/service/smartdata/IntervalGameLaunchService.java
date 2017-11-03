package com.hhly.smartdata.service.smartdata;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hhly.smartdata.dto.current.ChartSeriesData;
import com.hhly.smartdata.dto.current.IntervalGameLaunchTimeListReport;
import com.hhly.smartdata.mapper.smartdata.IntervalGameLaunchReportMapper;
import com.hhly.smartdata.service.source.SystemConfigServer;
import com.hhly.smartdata.util.HourListUtil;
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
    private SystemConfigServer systemConfigServer;

    /**
     * 获取游戏启动次数
     *
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectIntervalGameLaunchTimeListData() throws Exception{
        Map<String, Object> result = Maps.newHashMap();

        //时刻
        TreeSet<String> hourSet = HourListUtil.getHourListPerHour();
        result.put("time", hourSet);

        //平台信息
        Map<String, String> platformMap = systemConfigServer.getPlatformMap();

        Set<Object> listSet = Sets.newHashSet();
        Map<String, Integer> dataSum = Maps.newHashMap();

        for(Map.Entry<String, String> platform : platformMap.entrySet()){
            //统计数据
            List<IntervalGameLaunchTimeListReport> list = intervalGameLaunchReportMapper.selectIntervalGameLaunchTimeListData(platform.getKey());

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
            dataResult.put("name", platform.getValue());
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
     * @return
     * @throws Exception
     */
    public Map<String, Object> selectIntervalGameLaunchChartTimeData() throws Exception{
        Map<String, Object> result = Maps.newHashMap();

        //时刻
        TreeSet<String> hourSet = HourListUtil.getHourListPerHour();
        result.put("time", hourSet);

        //平台信息
        Map<String, String> platformMap = systemConfigServer.getPlatformMap();
        Set<String> platformSet = new HashSet<>();
        for(Map.Entry<String, String> platform : platformMap.entrySet()){
            platformSet.add(platform.getValue());
        }
        result.put("platform", platformSet);

        //统计数据
        List<ChartSeriesData> seriesData = new ArrayList<>();

        for(Map.Entry<String, String> platform : platformMap.entrySet()){
            ChartSeriesData csData = new ChartSeriesData();
            csData.setName(platform.getValue()); //平台名称
            csData.setType("line"); //线条类型

            //统计数据
            List<IntervalGameLaunchTimeListReport> list = intervalGameLaunchReportMapper.selectIntervalGameLaunchTimeListData(platform.getKey());

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
