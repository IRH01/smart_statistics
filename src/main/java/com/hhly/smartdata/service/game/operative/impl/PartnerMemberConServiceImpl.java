package com.hhly.smartdata.service.game.operative.impl;

import com.hhly.smartdata.mapper.game.operative.PartnerMemberConReposity;
import com.hhly.smartdata.model.game.operative.PartnerMemberCon;
import com.hhly.smartdata.service.game.operative.PartnerMemberConService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerMemberConServiceImpl implements PartnerMemberConService{
    @Autowired
    private PartnerMemberConReposity partnerMemberConReposity;

    @Override
    public PartnerMemberCon findById(String memberId){
        PartnerMemberCon value = partnerMemberConReposity.findById(memberId);
        return value;
    }

}
