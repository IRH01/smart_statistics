package com.hhly.smartdata.mapper.ybf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.InfoTypeStatH;

@Repository
public class InfoTypeStatHRepository extends BaseRepository {

	public List<InfoTypeStatH> getInfoTypeStatH(Map<String, String> param) {

		return this.getTemplate().selectList("infoTypeStatH.findInfoTypeStatH", param);
	}
}
