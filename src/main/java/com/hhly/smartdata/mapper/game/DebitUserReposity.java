package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.DebitUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DebitUserReposity extends BaseRepository{
    public List<DebitUser> find(Map<String, Object> conMap){
        List<DebitUser> values = template.selectList("debitUser.find", conMap);
        return values;
    }

    public int findCount(Map<String, Object> conMap){
        return template.selectOne("debitUser.count", conMap);
    }
}
