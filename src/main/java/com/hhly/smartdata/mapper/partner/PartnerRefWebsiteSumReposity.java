package com.hhly.smartdata.mapper.partner;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.partner.PartnerRefWebsiteSum;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PartnerRefWebsiteSumReposity extends BaseRepository{

    /**
     * 条件查询
     *
     * @param conMap
     * @return
     */
    public List<PartnerRefWebsiteSum> find(Map<String, Object> conMap){
        return super.template.selectList("partnerRefWebsiteSum.find", conMap);
    }

}
