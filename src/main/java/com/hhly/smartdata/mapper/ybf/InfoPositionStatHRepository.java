package com.hhly.smartdata.mapper.ybf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.InfoPositionStatH;

@Repository
public class InfoPositionStatHRepository extends BaseRepository {

	public List<InfoPositionStatH> getInfoTypeStatH(Map<String, Object> param) {

		return this.getTemplate().selectList("infoPositionStatH.findInfoPositionStatH", param);
	}
}
