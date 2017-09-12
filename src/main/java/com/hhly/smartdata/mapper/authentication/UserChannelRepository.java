package com.hhly.smartdata.mapper.authentication;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.model.authentication.UserChannel;

@Repository
public class UserChannelRepository extends BaseRepository{
	/**
	 * 根据userId删除
	 * @param userId
	 * @return
	 */
    public int deleteByUserId(Integer userId){
        return template.delete("userChannel.deleteByUserId", userId);
    }
    
    /**
     * 批量插入
     * @param userChannels
     * @return
     */
    public int batchAdd(List<UserChannel> userChannels){
    	return template.insert("userChannel.batchAdd", userChannels);
    }

    /**
     * 根据userId查询
     * @param userId
     * @return
     */
    public List<UserChannel> getByUserId(Integer userId){
        return template.selectList("userChannel.getByUserId", userId);
    }
}