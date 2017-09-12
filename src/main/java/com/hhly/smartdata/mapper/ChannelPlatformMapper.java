package com.hhly.smartdata.mapper;

import com.hhly.smartdata.model.ChannelPlatform;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelPlatformMapper{

    public List<ChannelPlatform> find(ChannelPlatform con);
}
