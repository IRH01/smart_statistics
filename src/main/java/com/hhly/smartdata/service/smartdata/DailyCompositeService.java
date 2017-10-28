package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.dto.daily.DailyCompositeReportResult;
import com.hhly.smartdata.mapper.smartdata.DailyCompositeReportMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyCompositeService{

    @Autowired
    private DailyCompositeReportMapper dailyCompositeReportMapper;


    public PageInfo<DailyCompositeReportResult> selectDailyCompositeListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        PageHelper.startPage(pageNumber, pageSize);
        List<DailyCompositeReportResult> dailyCompositeReportList = dailyCompositeReportMapper.selectDailyCompositeListData(startDate, endDate);
        PageInfo<DailyCompositeReportResult> pageInfo = new PageInfo<>(dailyCompositeReportList);
        return pageInfo;
    }

}
