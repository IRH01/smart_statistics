package com.hhly.smartdata.service.authentication;

import com.hhly.smartdata.mapper.authentication.AdminMapper;
import com.hhly.smartdata.mapper.authentication.UserChannelMapper;
import com.hhly.smartdata.mapper.authentication.UserMapper;
import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.model.authentication.UserChannel;
import com.hhly.smartdata.util.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService{

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserChannelMapper userChannelMapper;

    public List<Admin> searchAdmins(Admin condition, Page page){
        return adminMapper.searchAdmins(condition, page);
    }

    @Transactional
    public void save(Admin admin){
        userMapper.insert(admin);
        adminMapper.insert(admin);
        if(admin.getChannelIds().length() > 0){
            List<UserChannel> userChannels = new ArrayList<UserChannel>();
            if(admin.getChannelIds().indexOf(",") > 0){
                String[] channels = admin.getChannelIds().split(",");
                for(String item : channels){
                    UserChannel userChannel = new UserChannel();
                    userChannel.setChannelId(item);
                    userChannel.setUserId(admin.getUserId());
                    userChannels.add(userChannel);
                }
            }else{
                UserChannel userChannel = new UserChannel();
                userChannel.setUserId(admin.getUserId());
                userChannel.setChannelId(admin.getChannelIds());
                userChannels.add(userChannel);
            }
            userChannelMapper.batchAdd(userChannels);
        }
    }

    @Transactional
    public void update(Admin admin){
        userMapper.update(admin);
        adminMapper.update(admin);
        userChannelMapper.deleteByUserId(admin.getUserId());
        if(admin.getChannelIds().length() > 0){
            List<UserChannel> userChannels = new ArrayList<UserChannel>();
            if(admin.getChannelIds().indexOf(",") > 0){
                String[] channels = admin.getChannelIds().split(",");
                for(String item : channels){
                    UserChannel userChannel = new UserChannel();
                    userChannel.setChannelId(item);
                    userChannel.setUserId(admin.getUserId());
                    userChannel.setChannelId(item);
                    userChannels.add(userChannel);
                }
            }else{
                UserChannel userChannel = new UserChannel();
                userChannel.setUserId(admin.getUserId());
                userChannel.setChannelId(admin.getChannelIds());
                userChannels.add(userChannel);
            }
            userChannelMapper.batchAdd(userChannels);
        }
    }

    public Admin get(Integer id){
        return adminMapper.get(id);
    }

    @Transactional
    public int deleteByUserId(int userId){
        int result = adminMapper.deleteByUserId(userId);
        result = userMapper.delete(userId);
        return result;
    }

}
