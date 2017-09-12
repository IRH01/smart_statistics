package com.hhly.smartdata.service.ybf.impl;

import com.hhly.smartdata.mapper.ybf.InfoColStatDRepository;
import com.hhly.smartdata.model.ybf.InfoColStatD;
import com.hhly.smartdata.service.ybf.InfoColStatDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoColStatDServiceImpl implements InfoColStatDService{

    @Autowired
    private InfoColStatDRepository infoColStatDRepository;

    @Override
    public InfoColStatD getInfoColStatD(String infoType, String domain, String date){

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("infoType", infoType);
        param.put("domain", domain);
        param.put("date", date);

        return infoColStatDRepository.getInfoTypeStatD(param);
    }

}
