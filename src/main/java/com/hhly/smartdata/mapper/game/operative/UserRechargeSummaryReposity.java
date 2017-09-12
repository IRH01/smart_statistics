package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.UserRechargeSummary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRechargeSummaryReposity extends BaseRepository{
    public List<UserRechargeSummary> find(Map<String, Object> conditionMap){
        List<UserRechargeSummary> values = template.selectList("userRechargeSummary.find", conditionMap);
        return values;
    }

    public int count(Map<String, Object> conditionMap){
        int count = template.selectOne("userRechargeSummary.count", conditionMap);
        return count;
    }
}
