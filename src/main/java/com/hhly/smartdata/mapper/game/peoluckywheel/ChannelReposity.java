package com.hhly.smartdata.mapper.game.peoluckywheel;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.Channel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChannelReposity extends BaseRepository{
    public List<Channel> getAllAvailableChannelByDomain(String domainId){
        List<Channel> values = template.selectList("channel.getAllAvailableChannelByDomain", domainId);
        return values;
    }
}
