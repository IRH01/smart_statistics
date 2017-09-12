package com.hhly.smartdata.mapper.ybf;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.InfoColStatD;

@Repository
public class InfoColStatDRepository extends BaseRepository {

	public InfoColStatD getInfoTypeStatD(Map<String, Object> param) {

		return this.getTemplate().selectOne("infoColStatD.queryOne", param);
	}
}
