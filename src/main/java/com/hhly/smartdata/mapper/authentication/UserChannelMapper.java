package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.UserChannel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserChannelMapper extends BaseRepository{
    /**
     * 根据userId删除
     *
     * @param userId
     * @return
     */
    public int deleteByUserId(Integer userId){
        return template.delete("userChannel.deleteByUserId", userId);
    }

    /**
     * 批量插入
     *
     * @param userChannels
     * @return
     */
    public int batchAdd(List<UserChannel> userChannels){
        return template.insert("userChannel.batchAdd", userChannels);
    }

    /**
     * 根据userId查询
     *
     * @param userId
     * @return
     */
    public List<UserChannel> getByUserId(Integer userId){
        return template.selectList("userChannel.getByUserId", userId);
    }
}