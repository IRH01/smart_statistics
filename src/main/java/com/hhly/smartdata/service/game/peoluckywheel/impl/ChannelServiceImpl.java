package com.hhly.smartdata.service.game.peoluckywheel.impl;

import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.game.peoluckywheel.Channel;
import com.hhly.smartdata.mapper.game.peoluckywheel.ChannelReposity;
import com.hhly.smartdata.service.game.peoluckywheel.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelReposity channelReposity;
	
	@Override
	public JSONArray getAllAvailableChannelByDomain(String domainId) {
		List<Channel> value = channelReposity.getAllAvailableChannelByDomain(domainId);
		return JSONArray.fromObject(value);
	}
}
