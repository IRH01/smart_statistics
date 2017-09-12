package com.hhly.smartdata.service.ybf.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.ybf.InfoTypeStatD;
import com.hhly.smartdata.mapper.ybf.InfoTypeStatDRepository;
import com.hhly.smartdata.service.ybf.InfoTypeStatDService;

@Service
public class InfoTypeStatDServiceImpl implements InfoTypeStatDService {

	@Autowired
	private InfoTypeStatDRepository infoTypeStatDRepository;
	
	@Override
	public InfoTypeStatD getInfoTypeStatD(String infoType, String domain, String date) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("infoType", infoType);
		param.put("domain", domain);
		param.put("date", date);

		return infoTypeStatDRepository.getInfoTypeStatD(param);
	}

}
