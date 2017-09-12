package com.hhly.smartdata.service.ybf.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.dto.InfoStatDShower;
import com.hhly.smartdata.mapper.ybf.InfoStatDRepository;
import com.hhly.smartdata.service.ybf.InfoStatDService;

import net.sf.json.JSONObject;

@Service
public class InfoStatDServiceImpl implements InfoStatDService {

	@Autowired
	private InfoStatDRepository infoStatDRepository;

	@Override
	public JSONObject getInfoStatD(String domain, String date, String infoType, int pageNumber, int pageSize) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("domain", domain);
		param.put("date", date);
		param.put("infoType", infoType);

		// 分页查询
		PageHelper.startPage(pageNumber, pageSize);
		List<InfoStatDShower> infoStatDShowers = infoStatDRepository.getInfoStatD(param);
		PageInfo<InfoStatDShower> pageInfo = new PageInfo<InfoStatDShower>(infoStatDShowers);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("infoStatDShowers", pageInfo);
		resultMap.put("pageNumber", pageNumber);

		return JSONObject.fromObject(resultMap);
	}

	@Override
	public List<InfoStatDShower> getInfoStatDAll(String domain, String date, String infoType) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("domain", domain);
		param.put("date", date);
		param.put("infoType", infoType);

		return infoStatDRepository.getInfoStatD(param);
	}

}
