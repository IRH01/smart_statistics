package com.hhly.smartdata.service.smartdata;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.smartdata.DailyCompositeReportMapper;
import com.hhly.smartdata.model.smartdata.DailyCompositeReport;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DailyCompositeService{

    @Autowired
    private DailyCompositeReportMapper dailyCompositeReportMapper;


    public JSONObject selectDailyCompositeListData(String startDate, String endDate, int pageNumber, int pageSize) throws Exception{
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startDate", startDate);
        paramMap.put("endDate", endDate);
        PageHelper.startPage(pageNumber, pageSize);
        List<DailyCompositeReport> dailyCompositeReportList = dailyCompositeReportMapper.selectDailyCompositeListData(paramMap);
        PageInfo<DailyCompositeReport> pageInfo = new PageInfo<DailyCompositeReport>(dailyCompositeReportList);
        return JSONObject.fromObject(pageInfo);
    }


}
