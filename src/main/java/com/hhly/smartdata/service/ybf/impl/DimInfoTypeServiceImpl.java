package com.hhly.smartdata.service.ybf.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hhly.smartdata.dto.InfoTypeDto;
import com.hhly.smartdata.model.ybf.DimInfoType;
import com.hhly.smartdata.mapper.ybf.DimInfoTypeRepository;
import com.hhly.smartdata.service.ybf.DimInfoTypeService;

@Service
public class DimInfoTypeServiceImpl implements DimInfoTypeService {

	@Resource
	DimInfoTypeRepository dimInfoTypeRepository;

	@Override
	public List<InfoTypeDto> getAll(String date, String domain) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("date", date);
		param.put("domain", domain);

		return dimInfoTypeRepository.getAll(param);
	}

	@Override
	public DimInfoType get(Integer tblId) {
		return dimInfoTypeRepository.get(tblId);
	}
}
