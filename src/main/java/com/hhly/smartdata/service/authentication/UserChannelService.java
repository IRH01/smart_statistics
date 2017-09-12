package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.mapper.authentication.UserChannelMapper;
import com.hhly.smartdata.model.authentication.UserChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChannelService{
    @Autowired
    private UserChannelMapper userChannelMapper;

    public int deleteByUserId(Integer userId){
        return userChannelMapper.deleteByUserId(userId);
    }

    public int batchAdd(List<UserChannel> userChannels){
        return userChannelMapper.batchAdd(userChannels);
    }

    public List<UserChannel> getByUserId(Integer userId){
        return userChannelMapper.getByUserId(userId);
    }

}