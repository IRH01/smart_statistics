package com.hhly.smartdata.mapper.game.peoluckywheel;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.AlPeopleStatH;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AlPeopleStatHReposity extends BaseRepository{
    public List<AlPeopleStatH> find(Map<String, Object> conditionMap){
        List<AlPeopleStatH> values = template.selectList("alPeopleStatH.find", conditionMap);
        return values;
    }
}
