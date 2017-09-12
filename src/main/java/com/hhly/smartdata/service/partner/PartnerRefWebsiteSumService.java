package com.hhly.smartdata.service.partner;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.partner.PartnerRefWebsiteSum;

import java.util.Map;

public interface PartnerRefWebsiteSumService{
    public PageInfo<PartnerRefWebsiteSum> find(Map<String, Object> conditionMap, int pageNumber, int pageSize);
}
