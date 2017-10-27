package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervalTerminalsSourceService{


    @Autowired
    private IntervalSourceReportMapper intervalSourceReportMapper;

    public JSONObject selectIntervalTerminalsSourceListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        // 列表数据
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalTerminalsSourceListData(startDate, endDate);
        PageInfo<IntervalSourceReport> pageInfo = new PageInfo<IntervalSourceReport>(values);
        return JSONObject.fromObject(pageInfo);
    }

    public JSONObject selectIntervalTimeTerminalsSourceListData(String startDate, String endDate, int pageNumber, int pageSize, String deviceType) throws Exception{
        // 列表数据
        PageHelper.startPage(pageNumber, pageSize);
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalTimeTerminalsSourceListData(startDate, endDate, deviceType);
        PageInfo<IntervalSourceReport> pageInfo = new PageInfo<IntervalSourceReport>(values);
        return JSONObject.fromObject(pageInfo);
    }

}
