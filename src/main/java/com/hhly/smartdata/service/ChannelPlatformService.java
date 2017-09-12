package com.hhly.smartdata.service;

import com.google.common.collect.Lists;
import com.hhly.smartdata.mapper.authentication.UserChannelMapper;
import com.hhly.smartdata.mapper.ChannelPlatformMapper;
import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.model.authentication.UserChannel;
import com.hhly.smartdata.model.ChannelPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ChannelPlatformService{

    @Autowired
    private ChannelPlatformMapper channelPlatformMapper;
    @Autowired
    private UserChannelMapper userChannelMapper;

    public List<Node> getTreeNode(Integer userId){
        List<UserChannel> userChannels = Lists.newArrayList();
        if(userId != null && userId > 0){
            userChannels = userChannelMapper.getByUserId(userId);
        }
        List<Node> nodes = Lists.newArrayList();
        //查询全部
        List<ChannelPlatform> channelPlatforms = channelPlatformMapper.find(null);
        if(!CollectionUtils.isEmpty(channelPlatforms)){
            for(ChannelPlatform item : channelPlatforms){
                Node node = new Node();
                node.setId(item.getChannelId());
                node.setName(item.getChannelName());
                node.setData(item.getPlatformName());
                if(!CollectionUtils.isEmpty(userChannels)){
                    for(int i = 0; i < userChannels.size(); i++){
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

}
