package com.hhly.smartdata.service.authentication.impl;

import com.hhly.smartdata.mapper.authentication.UserChannelRepository;
import com.hhly.smartdata.model.authentication.UserChannel;
import com.hhly.smartdata.service.authentication.UserChannelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserChannelServiceImpl implements UserChannelService{
    @Resource
    private UserChannelRepository userChannelRepository;

    @Override
    public int deleteByUserId(Integer userId){
        return userChannelRepository.deleteByUserId(userId);
    }

    @Override
    public int batchAdd(List<UserChannel> userChannels){
        return userChannelRepository.batchAdd(userChannels);
    }

    @Override
    public List<UserChannel> getByUserId(Integer userId){
        return userChannelRepository.getByUserId(userId);
    }


}
