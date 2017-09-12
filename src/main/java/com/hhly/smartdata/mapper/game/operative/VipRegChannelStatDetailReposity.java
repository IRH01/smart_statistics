package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.PlatformInfo;
import com.hhly.smartdata.model.game.operative.VipRegChannelStatDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class VipRegChannelStatDetailReposity extends BaseRepository{

    /**
     * 条件查询
     *
     * @param conMap
     * @return
     */
    public List<VipRegChannelStatDetail> find(Map<String, Object> conMap){
        return super.template.selectList("vipRegChannelStatDetail.find", conMap);
    }

    /*
     * 获取平台信息
     */
    public List<PlatformInfo> getPlatformInfos(){
        return super.template.selectList("vipRegChannelStatDetail.getPlatformInfos");
    }

}
