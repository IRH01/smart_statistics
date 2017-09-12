package com.hhly.smartdata.mapper.game.operative;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.ChannelPlatform;

@Repository
public class ChannelPlatformReposity extends BaseRepository{
	
	/**
	 * 条件查询
	 * @param conMap
	 * @return
	 */
	public List<ChannelPlatform> find(ChannelPlatform con) {
		return super.template.selectList("channelPlatform.find",con);
	}
	
	/**
	 * 根据ChannelId查询
	 * @param channelId
	 * @return
	 */
	public ChannelPlatform findByChannelId(String channelId) {
		return (ChannelPlatform)super.template.selectOne("channelPlatform.findByChannelId",channelId);
	}
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	public boolean add(ChannelPlatform entity){
		int result = super.template.insert("channelPlatform.add", entity);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 删除
	 * @param channelId
	 * @return
	 */
	public boolean deleteById(String channelId){
		int result = super.getTemplate().delete("channelPlatform.deleteById", channelId);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 根据channelId获取ChannelName
	 * @param channelId
	 * @return
	 */
	public String findChannel(String channelId){
		return super.getTemplate().selectOne("channelPlatform.findChannel", channelId);
	}
	
	/**
	 * 条件精确查询
	 * @param conMap
	 * @return
	 */
	public List<ChannelPlatform> findExactly(ChannelPlatform con) {
		return super.template.selectList("channelPlatform.findExactly",con);
	}
}
