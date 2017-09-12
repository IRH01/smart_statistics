package com.hhly.smartdata.service.ybf;

import java.util.List;

import com.hhly.smartdata.dto.InfoTypeDto;
import com.hhly.smartdata.model.ybf.DimInfoType;

public interface DimInfoTypeService {
	/**
	 * 根据 id 获取
	 * 
	 * @param tblId
	 * @return
	 */
	public DimInfoType get(Integer tblId);

	/**
	 * 获取所有类别信息
	 * 
	 * @return
	 */
	public List<InfoTypeDto> getAll(String domain, String date);
}
