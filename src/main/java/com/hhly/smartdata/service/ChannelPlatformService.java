package com.hhly.smartdata.service;

import com.hhly.smartdata.mapper.authentication.UserChannelRepository;
import com.hhly.smartdata.mapper.game.operative.ChannelPlatformReposity;
import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.model.authentication.UserChannel;
import com.hhly.smartdata.model.game.operative.ChannelPlatform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChannelPlatformService{

    @Autowired
    private ChannelPlatformReposity channelPlatformReposity;
    @Autowired
    private UserChannelRepository userChannelRepository;

    public List<Node> getTreeNode(Integer userId){
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
