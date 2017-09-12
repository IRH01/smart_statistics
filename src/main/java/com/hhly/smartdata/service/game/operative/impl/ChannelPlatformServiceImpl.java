package com.hhly.smartdata.service.game.operative.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.model.authentication.UserChannel;
import com.hhly.smartdata.mapper.authentication.UserChannelRepository;
import com.hhly.smartdata.model.game.operative.ChannelPlatform;
import com.hhly.smartdata.mapper.game.operative.ChannelPlatformReposity;
import com.hhly.smartdata.service.game.operative.ChannelPlatformService;

@Service
public class ChannelPlatformServiceImpl implements ChannelPlatformService {

	@Autowired
	private ChannelPlatformReposity channelPlatformReposity;
	@Autowired
	private UserChannelRepository userChannelRepository;
	
	@Override
	public List<ChannelPlatform> find(ChannelPlatform condition, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ChannelPlatform> values = channelPlatformReposity.find(condition);
		return values;
	}

	@Override
	public boolean add(ChannelPlatform value) {
		return channelPlatformReposity.add(value);
	}

	@Override
	public boolean deleteById(String channelId) {
		return channelPlatformReposity.deleteById(channelId);
	}

	@Override
	public String findChannel(String channelId) {
		return channelPlatformReposity.findChannel(channelId);
	}

	@Override
	public ChannelPlatform findByChannelId(String channelId) {
		return channelPlatformReposity.findByChannelId(channelId);
	}

	@Override
	public List<Node> getTreeNode(Integer userId) {
		List<UserChannel> userChannels = new ArrayList<UserChannel>();
		if(userId != null && userId > 0){
			userChannels = userChannelRepository.getByUserId(userId);
		}
		List<Node> nodes = new ArrayList<Node>();
		//查询全部
		List<ChannelPlatform> channelPlatforms = channelPlatformReposity.find(null);
		if(!CollectionUtils.isEmpty(channelPlatforms)){
			for(ChannelPlatform item : channelPlatforms){
				Node node = new Node();
				node.setId(item.getChannelId());
				node.setName(item.getChannelName());
				node.setData(item.getPlatformName());
				if(!CollectionUtils.isEmpty(userChannels)){
					for(int i = 0;i < userChannels.size();i++){
						if(item.getChannelId().equals(userChannels.get(i).getChannelId())){
							node.setChecked(true);
							break;
						}
					}
				}
				nodes.add(node);
			}
		}
		return nodes;
	}

	@Override
	public List<ChannelPlatform> find(String channelId, String platformId) {
		ChannelPlatform condition = new ChannelPlatform();
		condition.setChannelId(channelId);
		condition.setPlatformId(platformId);
		List<ChannelPlatform> values = channelPlatformReposity.findExactly(condition);
		return values;
	}
}
