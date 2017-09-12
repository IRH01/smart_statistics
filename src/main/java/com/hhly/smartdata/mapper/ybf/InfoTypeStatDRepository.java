package com.hhly.smartdata.mapper.ybf;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.InfoTypeStatD;

@Repository
public class InfoTypeStatDRepository extends BaseRepository {

	public InfoTypeStatD getInfoTypeStatD(Map<String, Object> param) {
		
		 return  this.getTemplate().selectOne("infoTypeStatD.queryOne", param);
	}
}
