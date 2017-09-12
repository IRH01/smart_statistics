package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.UserChannel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserChannelMapper{

    int deleteByUserId(Integer userId);

    int batchAdd(List<UserChannel> userChannels);

    List<UserChannel> getByUserId(Integer userId);
}