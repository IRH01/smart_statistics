package com.hhly.smartdata.service.ybf.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.model.ybf.DimPosition;
import com.hhly.smartdata.mapper.ybf.DimPositionRepository;
import com.hhly.smartdata.service.ybf.DimPositionService;

@Service
public class DimPositionServiceImpl implements DimPositionService {

	@Resource
	DimPositionRepository locationRepository;
	
	@Override
	public DimPosition get(Integer tblId) {
		return locationRepository.get(tblId);
	}

	@Override
	public List<DimPosition> getDomains() {
		return (List<DimPosition>) locationRepository.getDomains();
	}

	@Override
	public List<Node> getPositions(String domainId) {
		List<Node> positions = locationRepository.getPositions(domainId);
		return positions;
	}
}
