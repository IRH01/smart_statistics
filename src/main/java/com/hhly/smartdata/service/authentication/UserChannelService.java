package com.hhly.smartdata.service.authentication;

import java.util.List;

import com.hhly.smartdata.model.authentication.UserChannel;

public interface UserChannelService{
	/**
	 * 根据userId删除
	 * @param userId
	 * @return
	 */
    public int deleteByUserId(Integer userId);
    
    /**
     * 批量插入
     * @param userChannels
     * @return
     */
    public int batchAdd(List<UserChannel> userChannels);
    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    public List<UserChannel> getByUserId(Integer userId);
}