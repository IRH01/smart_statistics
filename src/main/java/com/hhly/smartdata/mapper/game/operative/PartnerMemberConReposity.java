package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.PartnerMemberCon;
import org.springframework.stereotype.Repository;

@Repository
public class PartnerMemberConReposity extends BaseRepository{

    /**
     * 通过memberId查询
     *
     * @param memberId
     * @return
     */
    public PartnerMemberCon findById(String memberId){
        return super.template.selectOne("partnerMemberCon.findById", memberId);
    }

}
