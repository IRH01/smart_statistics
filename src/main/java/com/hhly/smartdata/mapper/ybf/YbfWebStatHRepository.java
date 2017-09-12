package com.hhly.smartdata.mapper.ybf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.dto.YbfWebStatH;

@Repository
public class YbfWebStatHRepository extends BaseRepository {

	public List<YbfWebStatH> findYbfWebStatH(Map<String, String> param) {

		return this.template.selectList("ybfWebStatH.find", param);
	}
}
