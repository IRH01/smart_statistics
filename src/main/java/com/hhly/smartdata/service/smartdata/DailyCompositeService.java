package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.DailyCompositeReportMapper;
import com.hhly.smartdata.model.smartdata.DailyCompositeReport;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyCompositeService{

    @Autowired
    private DailyCompositeReportMapper dailyCompositeReportMapper;


    public JSONObject selectDailyCompositeListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        PageHelper.startPage(pageNumber, pageSize);
        List<DailyCompositeReport> dailyCompositeReportList = dailyCompositeReportMapper.selectDailyCompositeListData(startDate, endDate);
        PageInfo<DailyCompositeReport> pageInfo = new PageInfo<DailyCompositeReport>(dailyCompositeReportList);
        return JSONObject.fromObject(pageInfo);
    }


}
