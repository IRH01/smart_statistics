package com.hhly.smartdata.mapper.game.peoluckywheel;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.AlPeopleStatD;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AlPeopleStatDReposity extends BaseRepository{
    public List<AlPeopleStatD> find(Map<String, Object> conditionMap){
        List<AlPeopleStatD> values = template.selectList("alPeopleStatD.find", conditionMap);
        return values;
    }
}
