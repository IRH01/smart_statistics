package com.hhly.smartdata.mapper.game.operative.lmdz;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.lmdz.LmdzChannelDaily;
import com.hhly.smartdata.model.game.operative.lmdz.LmdzChannelDailySum;

@Repository
public class LmdzChannelDailyReposity extends BaseRepository{
	
	/**
	 * 条件查询
	 * @param conMap
	 * @return
	 */
	public List<LmdzChannelDaily> find(Map<String, Object> condition) {
		return super.template.selectList("lmdzChannelDaily.find",condition);
	}
	
	/**
	 * 计算个数
	 * @param conMap
	 * @return
	 */
	public int count(Map<String, Object> condition) {
		return super.template.selectOne("lmdzChannelDaily.count",condition);
	}
	
	/**
	 * 汇总统计
	 * @param condition
	 * @return
	 */
	public LmdzChannelDailySum sum(Map<String, Object> condition) {
		return super.template.selectOne("lmdzChannelDaily.sum",condition);
	}
	
	/**
	 * 获取grades
	 * @param condition
	 * @return
	 */
	public List<String> getGrades(Map<String, Object> condition) {
		return super.template.selectList("lmdzChannelDaily.getGrades",condition);
	}
	
	/**
	 * 获取grades
	 * @param condition
	 * @return
	 */
	public List<LmdzChannelDaily> getOss(Map<String, Object> condition) {
		return super.template.selectList("lmdzChannelDaily.getOss",condition);
	}
}
