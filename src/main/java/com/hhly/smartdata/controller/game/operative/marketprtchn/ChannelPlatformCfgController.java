package com.hhly.smartdata.controller.game.operative.marketprtchn;

import java.util.Map;

import com.hhly.smartdata.util.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.google.common.collect.Maps;
import com.hhly.smartdata.constant.Constants;
import com.hhly.smartdata.dto.ControllerResult;
import com.hhly.smartdata.model.game.operative.ChannelPlatform;
import com.hhly.smartdata.service.game.PlatformInfoService;
import com.hhly.smartdata.service.game.operative.ChannelPlatformService;
import com.hhly.smartdata.util.page.Page;
import com.hhly.smartdata.util.page.PageUtil;

@Controller
@RequestMapping(value="/game/operative/chnplfcfg")
public class ChannelPlatformCfgController {
	@Autowired
	private ChannelPlatformService channelPlatformService;
	@Autowired 
	PlatformInfoService platformInfoService;
	
	@RequiresPermissions(value={"game:operative:chnplfcfg"})
	@RequestMapping(value="/list")
	public ModelAndView list(@ModelAttribute ChannelPlatform condition,@ModelAttribute Page page){
		 PageUtil.startPage(page);
	     Map<String,Object> model = Maps.newHashMap();
	     model.put("condition",condition);
	     model.put("channelPlatformList", this.channelPlatformService.find(condition, page.getPageNo(),page.getPageSize()));
		 return new ModelAndView("operative/game/operative/marketcprtchn/channelplatformcfg/list.main",model);
	}
	
	@RequiresPermissions(value={"game:operative:chnplfcfg"})
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(ModelMap modelMap){
		 modelMap.addAttribute("platforms", platformInfoService.getGamePlatformInfo(Integer.valueOf(Constants.COUNTRY_CN),""));
		 return new ModelAndView("operative/game/operative/marketcprtchn/channelplatformcfg/add.main");
	}
	
	@RequiresPermissions(value={"game:operative:chnplfcfg"})
	@RequestMapping(value="/add")
	@ResponseBody
	public Object add(String channelId,String platformId){
		ControllerResult result = new ControllerResult();
		ChannelPlatform entity = new ChannelPlatform();
		entity.setChannelId(channelId);
		entity.setPlatformId(platformId);
		if(!CollectionUtils.isEmpty(this.channelPlatformService.find(channelId, platformId))){
			//已存在channelId与platformId对应的记录
			result.setResult(-1);
		}else if(channelPlatformService.add(entity)){
			result.setResult(Result.OK);
		}else{
			result.setResult(-2);
		}
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:chnplfcfg"})
	@RequestMapping(value="/delete")
	@ResponseBody
	public Object delete(String channelId){
		ControllerResult result = new ControllerResult();
		channelPlatformService.deleteById(channelId);
		result.setResult(Result.OK);
		return result;
	}
	
	@RequiresPermissions(value={"game:operative:chnplfcfg"})
	@RequestMapping(value="/validateChannel")
	@ResponseBody
	public Object validateChannel(String channelId){
		ControllerResult result = new ControllerResult();
		ChannelPlatform condition = new ChannelPlatform();
		condition.setChannelId(channelId);
		String channelName = this.channelPlatformService.findChannel(channelId);
		//渠道号不存在
		if(null == channelName){
			result.setResult(-2);
			result.setValue(channelName);
		}else{
			//渠道号存在，校验成功
			result.setResult(Result.OK);
			result.setValue(channelName);
		}
		return result;
	}
}
