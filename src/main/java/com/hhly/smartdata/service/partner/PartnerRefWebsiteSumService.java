package com.hhly.smartdata.service.partner;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.partner.PartnerRefWebsiteSum;

public interface PartnerRefWebsiteSumService {
	public PageInfo<PartnerRefWebsiteSum> find(Map<String, Object> conditionMap,int pageNumber,int pageSize);
}
