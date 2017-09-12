package com.hhly.smartdata.mapper.ybf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.dto.InfoStatDShower;

@Repository
public class InfoStatDRepository extends BaseRepository {

	public List<InfoStatDShower> getInfoStatD(Map<String, String> param) {

		return this.getTemplate().selectList("infoStatD.findInfoStatD", param);
	}
}
