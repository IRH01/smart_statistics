package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.ActiveLossDaily;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ActiveLossDReposity extends BaseRepository{
    public List<ActiveLossDaily> find(Map<String, Object> conditionMap){
        List<ActiveLossDaily> values = template.selectList("activeLossDaily.find", conditionMap);
        return values;
    }
}
