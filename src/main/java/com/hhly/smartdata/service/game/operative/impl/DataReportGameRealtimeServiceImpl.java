package com.hhly.smartdata.service.game.operative.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.game.DataReportGameRealtimeReposity;
import com.hhly.smartdata.model.game.DataReportGameRealtime;
import com.hhly.smartdata.service.game.operative.DataReportGameRealtimeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataReportGameRealtimeServiceImpl implements DataReportGameRealtimeService{

    @Autowired
    private DataReportGameRealtimeReposity dataReportGameRealtimeReposity;

    @Override
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
                           int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<DataReportGameRealtime> values = dataReportGameRealtimeReposity.find(conditionMap);
        PageInfo<DataReportGameRealtime> pageInfo = new PageInfo<DataReportGameRealtime>(values);
        return JSONObject.fromObject(pageInfo);
    }
}
