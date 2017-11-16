package com.hhly.smartdata.service.interval;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hhly.smartdata.mapper.smartdata.IntervalInterfaceReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalInterfaceReport;
import com.hhly.smartdata.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public class IntervalInterfaceService{


    @Autowired
    IntervalInterfaceReportMapper intervalInterfaceReportMapper;

    public PageInfo<IntervalInterfaceReport> selectIntervalInterfaceTotalData(String startDate, String endDate) throws Exception{
        // 注册请求数 注册完成数 充值请求数 充值完成数
        Date now = new Date();
        String startTime = (StringUtils.isEmpty(startDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 00:00" : startDate) + ":00";
        String endTime = (StringUtils.isEmpty(endDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 24:00" : endDate) + ":00";
        PageHelper.startPage(1, 20);
        List<IntervalInterfaceReport> selectIntervalInterfaceTotalDataMap = intervalInterfaceReportMapper.selectIntervalInterfaceTotalData(startTime, endTime);
        return new PageInfo<>(selectIntervalInterfaceTotalDataMap);
    }

    public Map<String, Object> selectIntervalInterfaceChartData(String startDate, String endDate, String interfaceCode, TreeSet<String> scales) throws Exception{
        // 曲线图数据
        Map<String, Object> result = Maps.newHashMap();

        List<String> scaleList = Lists.newArrayList();
        //请求数
        List<Long> requestList = Lists.newArrayList();
        //requestList.add(0L);
        //完成数
        List<Long> completeList = Lists.newArrayList();
        //completeList.add(0L);

        Iterator<String> iterator = scales.iterator();
        Date now = new Date();
        String startTime = (StringUtils.isEmpty(startDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 00:00" : startDate) + ":00";
        String endTime = (StringUtils.isEmpty(endDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 24:00" : endDate) + ":00";
        //查询获取数据
        List<IntervalInterfaceReport> values = intervalInterfaceReportMapper.selectIntervalInterfaceChartData(startTime, endTime, interfaceCode);

        while(iterator.hasNext()){
            long requestNum = 0;
            long completeNum = 0;

            String currentScale = iterator.next();
            for(IntervalInterfaceReport value : values){

                // 匹配时间
                if(currentScale.substring(0, 5).equals(value.getStatisticsTime())){
                    if(value.getOperateType() == 1){//请求
                        requestNum = value.getOperateCount();

                    }else if(value.getOperateType() == 2){//完成
                        completeNum = value.getOperateCount();
                    }
                }
            }
            requestList.add(requestNum);
            completeList.add(completeNum);
            scaleList.add(currentScale);
        }

        result.put("scales", scaleList);
        result.put("requestList", requestList);
        result.put("completeList", completeList);

        return result;
    }
}
