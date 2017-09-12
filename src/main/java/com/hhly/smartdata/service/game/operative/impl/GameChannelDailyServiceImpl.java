package com.hhly.smartdata.service.game.operative.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.game.operative.GameChannelDailyReposity;
import com.hhly.smartdata.model.game.operative.GameChannelDaily;
import com.hhly.smartdata.service.game.operative.GameChannelDailyService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameChannelDailyServiceImpl implements GameChannelDailyService{

    @Autowired
    private GameChannelDailyReposity gameChannelDailyReposity;

    @Override
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
                           int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<GameChannelDaily> values = gameChannelDailyReposity.find(conditionMap);
        PageInfo<GameChannelDaily> pageInfo = new PageInfo<GameChannelDaily>(values);
        return JSONObject.fromObject(pageInfo);
    }
}
