package com.hhly.smartdata.mapper.partner;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.partner.PartnerRefWebsiteSum;

@Repository
public class PartnerRefWebsiteSumReposity extends BaseRepository{
	
	/**
	 * 条件查询
	 * @param conMap
	 * @return
	 */
	public List<PartnerRefWebsiteSum> find(Map<String, Object> conMap) {
		return super.template.selectList("partnerRefWebsiteSum.find",conMap);
	}

}
