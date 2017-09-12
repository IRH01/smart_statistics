package com.hhly.smartdata.service.game.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.game.RemainAnalysisDReposity;
import com.hhly.smartdata.model.game.RemainAnalysisDaily;
import com.hhly.smartdata.service.game.RemainAnalysisDailyService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RemainAnalysisDailyServiceImpl implements RemainAnalysisDailyService{
    @Autowired
    RemainAnalysisDReposity remainAnalysisDReposity;

    public JSONObject find(String platformId, String startDate, String endDate, int pageNumber, int pageSize){
        Map<String, Object> conditions = new HashMap<String, Object>();
        conditions.put("platformId", platformId);
        conditions.put("startDate", startDate);
        conditions.put("endDate", endDate);
        PageHelper.startPage(pageNumber, pageSize);
        List<RemainAnalysisDaily> values = remainAnalysisDReposity.find(conditions);
        PageInfo<RemainAnalysisDaily> pageInfo = new PageInfo<RemainAnalysisDaily>(values);
        return JSONObject.fromObject(pageInfo);
    }
}
