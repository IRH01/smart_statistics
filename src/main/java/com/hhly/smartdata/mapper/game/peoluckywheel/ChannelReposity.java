package com.hhly.smartdata.mapper.game.peoluckywheel;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.Channel;

@Repository
public class ChannelReposity extends BaseRepository{
	public List<Channel> getAllAvailableChannelByDomain(String domainId){
		List<Channel> values = template.selectList("channel.getAllAvailableChannelByDomain",domainId);
		return values;
	}
}
