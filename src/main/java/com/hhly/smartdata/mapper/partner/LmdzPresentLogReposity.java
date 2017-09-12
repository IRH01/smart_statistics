package com.hhly.smartdata.mapper.partner;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.partner.LmdzPresentLog;

@Repository
public class LmdzPresentLogReposity extends BaseRepository{
	
	/**
	 * 条件查询合作伙伴合格会员下所有信息
	 * @param conMap
	 * @return
	 */
	public List<LmdzPresentLog> find(Map<String, Object> conMap) {
		return super.template.selectList("lmdzPresentLog.find",conMap);
	}

	/**
	 * 条件查询合作伙伴合格会员下所有信息
	 * @param conMap
	 * @return
	 */
	public int count(Map<String, Object> conMap) {
		return super.template.selectOne("lmdzPresentLog.count",conMap);
	}
}
