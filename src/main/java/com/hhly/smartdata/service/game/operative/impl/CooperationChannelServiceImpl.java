package com.hhly.smartdata.service.game.operative.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.operative.CooperationChannelStat;
import com.hhly.smartdata.mapper.game.operative.CooperationChannelStatReposity;
import com.hhly.smartdata.service.game.operative.CooperationChannelService;

@Service
public class CooperationChannelServiceImpl implements CooperationChannelService {
	@Autowired
	private CooperationChannelStatReposity cooperationChannelStatReposity;
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<CooperationChannelStat> values = cooperationChannelStatReposity.find(conditionMap);
		PageInfo<CooperationChannelStat> pageInfo = new PageInfo<CooperationChannelStat>(values);
		return JSONObject.fromObject(pageInfo);
	}
}
