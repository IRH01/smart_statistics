package com.hhly.smartdata.service.partner;

import com.hhly.smartdata.model.partner.GameType;
import com.hhly.smartdata.model.partner.PartnerMemberBlaDetail;

import java.util.List;
import java.util.Map;

public interface PartnerMemberBlaDetailService{
    /**
     * 条件查询
     *
     * @param conMap
     * @return
     */
    public List<PartnerMemberBlaDetail> find(Map<String, Object> conMap);


    /**
     * 查询获取一条记录
     *
     * @param monthId
     * @param partnerUserId
     * @param gameTypeId
     * @param rackBackModel
     * @return
     */
    public PartnerMemberBlaDetail findOne(String monthId, String partnerUserId, int gameTypeId, int rackBackModel);

    /**
     * 条件查询
     *
     * @param conMap
     * @return
     */
    public List<GameType> findGameType(Map<String, Object> conMap);
}
