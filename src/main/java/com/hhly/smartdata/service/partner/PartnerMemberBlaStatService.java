package com.hhly.smartdata.service.partner;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.partner.PartnerMemberBlaStat;

import java.util.Map;

public interface PartnerMemberBlaStatService{
    public PageInfo<PartnerMemberBlaStat> find(Map<String, Object> conditionMap, int pageNumber, int pageSize);

    public String export(Map<String, Object> conditionMap);

    public boolean canExport(Map<String, Object> conditionMap);
}
