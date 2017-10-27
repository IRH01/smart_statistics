package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hhly.smartdata.mapper.smartdata.IntervalInterfaceReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalInterfaceReport;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public class IntervalInterfaceService{


    @Autowired
    IntervalInterfaceReportMapper intervalInterfaceReportMapper;

    public JSONObject selectIntervalInterfaceToltalData(String startDate, String endDate) throws Exception{
        // 注册请求数 注册完成数 充值请求数 充值完成数
        PageHelper.startPage(1, 20);
        List<IntervalInterfaceReport> selectIntervalInterfaceTotalDataMap = intervalInterfaceReportMapper.selectIntervalInterfaceTotalData(startDate, endDate);
        PageInfo<IntervalInterfaceReport> pageInfo = new PageInfo<IntervalInterfaceReport>(selectIntervalInterfaceTotalDataMap);
        return JSONObject.fromObject(pageInfo);

    }

    public JSONObject selectIntervalInterfaceChartData(String startDate, String endDate, String interfaceCode, TreeSet<String> scales) throws Exception{
        // 曲线图数据
        Map<String, Object> result = Maps.newHashMap();

        List<String> scaleList = Lists.newLinkedList();
        //请求数
        List<Long> requestList = Lists.newLinkedList();
        //完成数
        List<Long> completeList = Lists.newLinkedList();

        Iterator<String> iterator = scales.iterator();

        //查询获取数据
        List<IntervalInterfaceReport> values = intervalInterfaceReportMapper.selectIntervalInterfaceChartData(startDate, endDate, interfaceCode);

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

        return JSONObject.fromObject(result);
    }
}
