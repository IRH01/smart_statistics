package com.hhly.smartdata.service.game.operative.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.game.DataReportPlatformRealtimeReposity;
import com.hhly.smartdata.model.game.DataReportPlatformRealtime;
import com.hhly.smartdata.service.game.operative.DataReportPlatformRealtimeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataReportPlatformRealtimeServiceImpl implements DataReportPlatformRealtimeService{

    @Autowired
    private DataReportPlatformRealtimeReposity dataReportPlatformRealtimeReposity;

    @Override
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
                           int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<DataReportPlatformRealtime> values = dataReportPlatformRealtimeReposity.find(conditionMap);
        PageInfo<DataReportPlatformRealtime> pageInfo = new PageInfo<DataReportPlatformRealtime>(values);
        return JSONObject.fromObject(pageInfo);
    }
}
