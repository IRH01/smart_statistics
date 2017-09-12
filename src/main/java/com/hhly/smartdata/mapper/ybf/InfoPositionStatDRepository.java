package com.hhly.smartdata.mapper.ybf;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.dto.InfoByPosition;
import com.hhly.smartdata.dto.PositionByInfo;

@Repository
public class InfoPositionStatDRepository extends BaseRepository {

	public List<PositionByInfo> getInfoPositionStatDByInfo(Map<String, String> param) {

		return this.template.selectList("infoPositionStatD.findPositionByInfo", param);
	}

	public List<InfoByPosition> getInfoPositionStatDByPosition(Map<String, Object> param) {

		return this.template.selectList("infoPositionStatD.findInfoByPosition", param);
	}
}
