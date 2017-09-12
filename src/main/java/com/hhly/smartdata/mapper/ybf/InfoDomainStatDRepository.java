package com.hhly.smartdata.mapper.ybf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.InfoDomainStatD;

@Repository
public class InfoDomainStatDRepository extends BaseRepository {

	public List<InfoDomainStatD> getInfoActStatD(Map<String, String> params) {

		return this.getTemplate().selectList("infoDomainStatD.findByDate", params);
	}
}
