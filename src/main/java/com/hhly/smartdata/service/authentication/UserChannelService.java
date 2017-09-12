package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.mapper.authentication.UserChannelRepository;
import com.hhly.smartdata.model.authentication.UserChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserChannelService{
    @Autowired
    private UserChannelRepository userChannelRepository;

    public int deleteByUserId(Integer userId){
        return userChannelRepository.deleteByUserId(userId);
    }

    public int batchAdd(List<UserChannel> userChannels){
        return userChannelRepository.batchAdd(userChannels);
    }

    public List<UserChannel> getByUserId(Integer userId){
        return userChannelRepository.getByUserId(userId);
    }

}