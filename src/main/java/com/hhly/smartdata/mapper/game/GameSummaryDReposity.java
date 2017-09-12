package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.GameSummaryDaily;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GameSummaryDReposity extends BaseRepository{
    public List<GameSummaryDaily> find(Map<String, Object> conditionMap){
        List<GameSummaryDaily> values = template.selectList("gameSummaryDaily.find", conditionMap);
        return values;
    }
}
