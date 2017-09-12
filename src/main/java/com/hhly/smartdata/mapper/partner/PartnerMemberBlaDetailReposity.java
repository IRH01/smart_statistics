package com.hhly.smartdata.mapper.partner;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.partner.GameType;
import com.hhly.smartdata.model.partner.PartnerMemberBlaDetail;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PartnerMemberBlaDetailReposity extends BaseRepository{

    /**
     * 条件查询
     *
     * @param conMap
     * @return
     */
    public List<PartnerMemberBlaDetail> find(Map<String, Object> conMap){
        return super.template.selectList("partnerMemberBlaDetail.find", conMap);
    }


    /**
     * 查询获取一条记录
     *
     * @param monthId
     * @param partnerUserId
     * @param gameTypeId
     * @return
     */
    public PartnerMemberBlaDetail findOne(String monthId, String partnerUserId, int gameTypeId, int rackBackModel){
        Map<String, Object> conMap = new HashMap<String, Object>();
        conMap.put("monthId", monthId);
        conMap.put("partnerUserId", partnerUserId);
        conMap.put("gameTypeId", gameTypeId);
        conMap.put("rackBackModel", rackBackModel);
        return super.template.selectOne("partnerMemberBlaDetail.findOne", conMap);
    }

    /**
     * 查询游戏类型
     *
     * @param conMap
     * @return
     */
    public List<GameType> findGameType(Map<String, Object> conMap){
        return super.template.selectList("partnerMemberBlaDetail.findGameType", conMap);
    }
}
