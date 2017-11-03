package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.IntervalSourceReportMapper;
import com.hhly.smartdata.model.smartdata.IntervalSourceReport;
import com.hhly.smartdata.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IntervalSourceService{

    @Autowired
    private IntervalSourceReportMapper intervalSourceReportMapper;

    public List<IntervalSourceReport> selectTotalDaySourceListData() throws Exception{
        Date now = new Date();
        String startTime = DateUtil.date2String(DateUtil.getNowZeroTime(now));
        String endTime = DateUtil.date2String(DateUtil.getNowLongestTime(now));
        // 列表数据
        return intervalSourceReportMapper.selectIntervalTerminalsSourceListData(startTime, endTime);
    }

    public PageInfo<IntervalSourceReport> selectIntervalTimeTerminalsSourceListData(String startDate, String endDate, int pageNumber, int pageSize, String deviceType) throws Exception{
        Date now = new Date();
        // 列表数据
        PageHelper.startPage(pageNumber, pageSize);
        String startTime = (StringUtils.isEmpty(startDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 00:00" : startDate) + ":00";
        String endTime = (StringUtils.isEmpty(endDate) ? DateUtil.date2String(now, "yyyy-MM-dd") + " 24:00" : endDate) + ":00";
        List<IntervalSourceReport> values = intervalSourceReportMapper.selectIntervalTimeTerminalsSourceListData(startTime, endTime, deviceType);
        return new PageInfo<>(values);
    }

}
