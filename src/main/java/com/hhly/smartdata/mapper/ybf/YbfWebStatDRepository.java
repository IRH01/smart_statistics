package com.hhly.smartdata.mapper.ybf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.dto.YbfWebStatD;

@Repository
public class YbfWebStatDRepository extends BaseRepository {

	/**
	 * 查询获取日统计数据
	 * @param map
	 * @return
	 */
	public List<YbfWebStatD> findYbfWebStatD(Map<String, Object> map) {
		return this.template.selectList("ybfWebStatD.find", map);
	}
	
	/**
	 * 累计计算日统计数据
	 * @param map
	 * @return
	 */
	public List<YbfWebStatD> countYbfWebStatD(Map<String, Object> map) {
		return this.template.selectList("ybfWebStatD.count", map);
	}
}
