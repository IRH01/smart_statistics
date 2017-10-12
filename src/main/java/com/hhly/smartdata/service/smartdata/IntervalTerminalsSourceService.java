package com.hhly.smartdata.service.smartdata;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

@Service
public class IntervalTerminalsSourceService{


    @Autowired
    private IntervalSourceReportMapper intervalSourceReportMapper;

    public JSONObject selectIntervalTerminalsSourceListData(String startDate,String endDate,int pageNumber,int pageSize) throws Exception{
        // 列表数据
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("startDate", startDate);
        condition.put("endDate", endDate);
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalTerminalsSourceListData(condition);
        PageInfo<IntervalSourceReport> pageInfo = new PageInfo<IntervalSourceReport>(values);
        return JSONObject.fromObject(pageInfo);
    }

    public JSONObject selectIntervalTimeTerminalsSourceListData(String startDate,String endDate,int pageNumber,int pageSize,String deviceType) throws Exception{
        // 列表数据
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("startDate", startDate);
        condition.put("endDate", endDate);
        condition.put("deviceType",deviceType);
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalTimeTerminalsSourceListData(condition);
        PageInfo<IntervalSourceReport> pageInfo = new PageInfo<IntervalSourceReport>(values);
        return JSONObject.fromObject(pageInfo);
    }

}
