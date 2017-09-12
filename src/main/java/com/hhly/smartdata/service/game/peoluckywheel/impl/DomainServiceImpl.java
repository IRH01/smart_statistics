package com.hhly.smartdata.service.game.peoluckywheel.impl;

import com.hhly.smartdata.mapper.game.peoluckywheel.DomainReposity;
import com.hhly.smartdata.model.game.peoluckywheel.Domain;
import com.hhly.smartdata.service.game.peoluckywheel.DomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainServiceImpl implements DomainService{
    @Autowired
    private DomainReposity domainReposity;

    @Override
    public List<Domain> getAllAvailableDomain(){
        List<Domain> value = domainReposity.getAllAvailableDomain();
        return value;
    }
}
