package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.CooperationChannelStat;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CooperationChannelStatReposity extends BaseRepository{

    /**
     * 条件查询
     *
     * @param conMap
     * @return
     */
    public List<CooperationChannelStat> find(Map<String, Object> conMap){
        return super.template.selectList("cooperationChannelStat.find", conMap);
    }
}
