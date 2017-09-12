package com.hhly.smartdata.mapper.partner;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.partner.LydjBettingLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LydjBettingLogReposity extends BaseRepository{

    /**
     * 条件查询合作伙伴合格会员下所有信息
     *
     * @param conMap
     * @return
     */
    public List<LydjBettingLog> find(Map<String, Object> conMap){
        return super.template.selectList("lydjBettingLog.find", conMap);
    }

    /**
     * 条件查询合作伙伴合格会员下所有信息
     *
     * @param conMap
     * @return
     */
    public int count(Map<String, Object> conMap){
        return super.template.selectOne("lydjBettingLog.count", conMap);
    }
}
