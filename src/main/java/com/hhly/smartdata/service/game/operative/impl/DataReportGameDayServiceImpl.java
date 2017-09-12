package com.hhly.smartdata.service.game.operative.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.game.DataReportGameDayReposity;
import com.hhly.smartdata.model.game.DataReportGameDay;
import com.hhly.smartdata.service.game.operative.DataReportGameDayService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataReportGameDayServiceImpl implements DataReportGameDayService{

    @Autowired
    private DataReportGameDayReposity dataReportGameDayReposity;

    @Override
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
                           int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<DataReportGameDay> values = dataReportGameDayReposity.find(conditionMap);
        PageInfo<DataReportGameDay> pageInfo = new PageInfo<DataReportGameDay>(values);
        return JSONObject.fromObject(pageInfo);
    }

    @Override
    public JSONObject findList(Map<String, Object> conditionMap,
                               int pageNumber, int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<DataReportGameDay> values = dataReportGameDayReposity.findList(conditionMap);
        PageInfo<DataReportGameDay> pageInfo = new PageInfo<DataReportGameDay>(values);
        return JSONObject.fromObject(pageInfo);
    }

}
