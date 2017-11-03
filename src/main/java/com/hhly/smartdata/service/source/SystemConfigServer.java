package com.hhly.smartdata.service.source;

import com.hhly.smartdata.mapper.member.SystemConfigMapper;
import com.hhly.smartdata.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/11/1.
 */
@Service
public class SystemConfigServer{

    @Autowired
    private SystemConfigMapper systemConfigMapper;

    public Map<String, String> getPlatformMap() throws Exception{
        String configValue = systemConfigMapper.getConfigValueByKey("hhly:playOne:systemConfig:common:platformCode");
        return JsonUtil.jsonStr2Map(configValue);
    }
}
