package com.hhly.smartdata.service.game.operative.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.game.operative.VipRegChannelStatDetailReposity;
import com.hhly.smartdata.model.game.PlatformInfo;
import com.hhly.smartdata.model.game.operative.VipRegChannelStatDetail;
import com.hhly.smartdata.service.game.operative.VipRegChannelStatDetailService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VipRegChannelStatDetailServiceImpl implements
        VipRegChannelStatDetailService{
    @Autowired
    VipRegChannelStatDetailReposity vipRegChannelStatDetailReposity;

    @Override
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
                           int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<VipRegChannelStatDetail> values = vipRegChannelStatDetailReposity.find(conditionMap);
        PageInfo<VipRegChannelStatDetail> pageInfo = new PageInfo<VipRegChannelStatDetail>(values);
        return JSONObject.fromObject(pageInfo);
    }

    @Override
    public List<PlatformInfo> getPlatformInfos(){
        return vipRegChannelStatDetailReposity.getPlatformInfos();
    }
}
