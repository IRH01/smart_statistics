package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.VipRegChannelStatSum;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VipRegChannelStatSumReposity extends BaseRepository{

    /**
     * 条件查询
     *
     * @param conMap
     * @return
     */
    public List<VipRegChannelStatSum> find(Map<String, Object> conMap){
        return super.template.selectList("vipRegChannelStatSum.find", conMap);
    }

}
