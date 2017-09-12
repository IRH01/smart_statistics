package com.hhly.smartdata.mapper.partner;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.partner.LmdzBettingLog;

@Repository
public class LmdzBettingLogReposity extends BaseRepository{
	
	/**
	 * 条件查询合作伙伴合格会员下所有信息
	 * @param conMap
	 * @return
	 */
	public List<LmdzBettingLog> find(Map<String, Object> conMap) {
		return super.template.selectList("lmdzBettingLog.find",conMap);
	}

	/**
	 * 条件查询合作伙伴合格会员下所有信息
	 * @param conMap
	 * @return
	 */
	public int count(Map<String, Object> conMap) {
		return super.template.selectOne("lmdzBettingLog.count",conMap);
	}
}
