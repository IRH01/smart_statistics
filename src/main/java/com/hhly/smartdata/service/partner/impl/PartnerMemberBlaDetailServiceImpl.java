package com.hhly.smartdata.service.partner.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.partner.GameType;
import com.hhly.smartdata.model.partner.PartnerMemberBlaDetail;
import com.hhly.smartdata.mapper.partner.PartnerMemberBlaDetailReposity;
import com.hhly.smartdata.service.partner.PartnerMemberBlaDetailService;

@Service
public class PartnerMemberBlaDetailServiceImpl implements PartnerMemberBlaDetailService {
	@Autowired
	private PartnerMemberBlaDetailReposity partnerMemberBlaDetailReposity;
	
	@Override
	public List<PartnerMemberBlaDetail> find(Map<String, Object> conMap) {
		return partnerMemberBlaDetailReposity.find(conMap);
	}

	@Override
	public PartnerMemberBlaDetail findOne(String monthId, String partnerUserId,
			int gameTypeId,int rackBackModel) {
		return partnerMemberBlaDetailReposity.findOne(monthId, partnerUserId, gameTypeId,rackBackModel);
	}

	@Override
	public List<GameType> findGameType(Map<String, Object> conMap) {
		return partnerMemberBlaDetailReposity.findGameType(conMap);
	}
}
