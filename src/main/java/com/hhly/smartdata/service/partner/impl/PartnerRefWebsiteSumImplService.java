package com.hhly.smartdata.service.partner.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.partner.PartnerRefWebsiteSumReposity;
import com.hhly.smartdata.model.partner.PartnerRefWebsiteSum;
import com.hhly.smartdata.service.partner.PartnerRefWebsiteSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PartnerRefWebsiteSumImplService implements PartnerRefWebsiteSumService{
    @Autowired
    private PartnerRefWebsiteSumReposity partnerRefWebsiteSumReposity;

    @Override
    public PageInfo<PartnerRefWebsiteSum> find(Map<String, Object> conditionMap,
                                               int pageNumber, int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<PartnerRefWebsiteSum> values = partnerRefWebsiteSumReposity.find(conditionMap);
        PageInfo<PartnerRefWebsiteSum> pageInfo = new PageInfo<PartnerRefWebsiteSum>(values);
        return pageInfo;
    }
}
