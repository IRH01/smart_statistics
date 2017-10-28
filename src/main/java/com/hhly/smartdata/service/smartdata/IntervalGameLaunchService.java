package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hhly.smartdata.mapper.smartdata.IntervalGameLaunchReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalGameLaunchListReport;
import com.hhly.smartdata.model.smartdata.IntervalGameLaunchReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public class IntervalGameLaunchService{

    @Autowired
    private IntervalGameLaunchReportMapper intervalGameLaunchReportMapper;


    public PageInfo<IntervalGameLaunchListReport> selectIntervalGameLaunchListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalGameLaunchListReport> values = intervalGameLaunchReportMapper.selectIntervalGameLaunchListData(startDate, endDate);
        PageInfo<IntervalGameLaunchListReport> pageInfo = new PageInfo<>(values);
        return pageInfo;
    }

    public Map<String, Object> selectIntervalGameLaunchChartData(String startDate, String endDate, TreeSet<String> scales) throws Exception{
        // 曲线图数据
        Map<String, Object> result = Maps.newHashMap();
        List<String> scaleList = Lists.newLinkedList();
        // 一比分
        List<Long> ybfList = Lists.newLinkedList();
        // 乐盈电竞
        List<Long> yydjList = Lists.newLinkedList();
        // 撩妹德州
        List<Long> lmdzList = Lists.newLinkedList();

        Iterator<String> iterator = scales.iterator();

        //查询获取数据
        List<IntervalGameLaunchReport> values = intervalGameLaunchReportMapper.selectIntervalGameLaunchChatData(startDate, endDate);

        while(iterator.hasNext()){
            long ybfNum = 0;
            long yydjNum = 0;
            long lmdzNum = 0;

            String currentScale = iterator.next();
            for(IntervalGameLaunchReport value : values){
                // 匹配时间
                if(currentScale.substring(0, 5).equals(value.getStatisticsTime())){
                    if("撩妹德州".equals(value.getPlatformName())){
                        lmdzNum = value.getLaunchCount();
                    }else if("乐盈电竞".equals(value.getPlatformName())){
                        yydjNum = value.getLaunchCount();
                    }else if("一比分".equals(value.getPlatformName())){
                        ybfNum = value.getLaunchCount();
                    }

                }
            }
            ybfList.add(ybfNum);
            yydjList.add(yydjNum);
            lmdzList.add(lmdzNum);
            scaleList.add(currentScale);
        }

        result.put("scales", scaleList);
        result.put("ybfList", ybfList);
        result.put("yydjList", yydjList);
        result.put("lmdzList", lmdzList);

        return result;
    }

}
