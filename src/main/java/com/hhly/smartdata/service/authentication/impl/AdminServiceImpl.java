package com.hhly.smartdata.service.authentication.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.model.authentication.UserChannel;
import com.hhly.smartdata.mapper.authentication.AdminRepository;
import com.hhly.smartdata.mapper.authentication.UserChannelRepository;
import com.hhly.smartdata.mapper.authentication.UserRepository;
import com.hhly.smartdata.service.authentication.AdminService;
import com.hhly.smartdata.util.page.Page;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminRepository adminRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserChannelRepository userChannelRepository;

    @Override
    public List<Admin> searchAdmins(Admin condition,Page page) {
        return adminRepository.searchAdmins(condition,page);
    }

    @Override
    @Transactional
    public void save(Admin admin) {
        userRepository.insert(admin);
        adminRepository.insert(admin);
        if(admin.getChannelIds().length()>0){
        	List<UserChannel> userChannels = new ArrayList<UserChannel>();
            if(admin.getChannelIds().indexOf(",")>0){
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
		    userChannelRepository.batchAdd(userChannels);
        }
    }

    @Override
    @Transactional
    public void update(Admin admin){
    	userRepository.update(admin);
    	adminRepository.update(admin);
    	userChannelRepository.deleteByUserId(admin.getUserId());
		if(admin.getChannelIds().length()>0){
		 	List<UserChannel> userChannels = new ArrayList<UserChannel>();
		    if(admin.getChannelIds().indexOf(",")>0){
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
		    userChannelRepository.batchAdd(userChannels);
		}
    }

    @Override
    public Admin get(Integer id) {
        return adminRepository.get(id);
    }

    @Override
    @Transactional
	public int deleteByUserId(int userId) {
		int result = adminRepository.deleteByUserId(userId);
		result = userRepository.delete(userId);
		return result;
	}

}
