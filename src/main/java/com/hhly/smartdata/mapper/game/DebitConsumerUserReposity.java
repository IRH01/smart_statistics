package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.ConsumerUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DebitConsumerUserReposity extends BaseRepository{
    public List<ConsumerUser> find(Map<String, Object> conMap){
        List<ConsumerUser> values = template.selectList("consumerUser.find", conMap);
        return values;
    }

    public List<ConsumerUser> findCon(Map<String, Object> conMap){
        List<ConsumerUser> values = template.selectList("consumerUser.findCon", conMap);
        return values;
    }

    public int findCount(Map<String, Object> conMap){
        return template.selectOne("consumerUser.count", conMap);
    }

    public int findCountList(Map<String, Object> conMap){
        return template.selectOne("consumerUser.countList", conMap);
    }
}
