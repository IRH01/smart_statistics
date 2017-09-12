package com.hhly.smartdata.mapper.partner;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.partner.PartnerMemberBlaStat;

@Repository
public class PartnerMemberBlaStatReposity extends BaseRepository{
	
	/**
	 * 条件查询
	 * @param conMap
	 * @return
	 */
	public List<PartnerMemberBlaStat> find(Map<String, Object> conMap) {
		return super.template.selectList("partnerMemberBlaStat.find",conMap);
	}

	/**
	 * 统计
	 * @param conMap
	 * @return
	 */
	public int count(Map<String, Object> conMap) {
		return super.template.selectOne("partnerMemberBlaStat.count",conMap);
	}
}
