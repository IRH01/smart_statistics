package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.PayLossDaily;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PayLossDReposity extends BaseRepository{
    public List<PayLossDaily> find(Map<String, Object> conditionMap){
        List<PayLossDaily> values = template.selectList("payLossDaily.find", conditionMap);
        return values;
    }
}
