package com.hhly.smartdata.service.game.operative.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.operative.VipRegChannelStatSum;
import com.hhly.smartdata.mapper.game.operative.VipRegChannelStatSumReposity;
import com.hhly.smartdata.service.game.operative.VipRegChannelStatSumService;

@Service
public class VipRegChannelStatSumServiceImpl implements
		VipRegChannelStatSumService {
	@Autowired
	VipRegChannelStatSumReposity vipRegChannelStatSumReposity;
	
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<VipRegChannelStatSum> values = vipRegChannelStatSumReposity.find(conditionMap);
		PageInfo<VipRegChannelStatSum> pageInfo = new PageInfo<VipRegChannelStatSum>(values);
		return JSONObject.fromObject(pageInfo);
	}

}
