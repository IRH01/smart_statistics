package com.hhly.smartdata.mapper;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ChannelPlatform;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChannelPlatformMapper extends BaseRepository{

    public List<ChannelPlatform> find(ChannelPlatform con){
        return super.template.selectList("channelPlatform.find", con);
    }
}
