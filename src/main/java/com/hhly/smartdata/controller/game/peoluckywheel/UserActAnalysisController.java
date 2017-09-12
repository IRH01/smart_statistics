package com.hhly.smartdata.controller.game.peoluckywheel;

import java.util.TreeSet;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.service.game.peoluckywheel.AlPeopleStatDailyService;
import com.hhly.smartdata.service.game.peoluckywheel.AlPeopleStatHourlyService;
import com.hhly.smartdata.service.game.peoluckywheel.ChannelService;
import com.hhly.smartdata.service.game.peoluckywheel.DomainService;
import com.hhly.smartdata.service.game.peoluckywheel.GamePageService;
import com.hhly.smartdata.util.DateListUtil;
import com.hhly.smartdata.util.HourListUtil;

@Controller
@RequestMapping(value="/game/peoluckywheel/useractals")
public class UserActAnalysisController {
	@Autowired
	private DomainService domainService;
	@Autowired
	private AlPeopleStatDailyService alPeopleStatDailyService;
	@Autowired
	private AlPeopleStatHourlyService alPeopleStatHourlyService;
	@Autowired
	private GamePageService gamePageService;
	@Autowired
	private ChannelService channelService;
	
	@RequestMapping(value="/show")
	@RequiresPermissions(value={"peoluckywheel:useractals"})
	public ModelAndView show(ModelMap modeMap){
		modeMap.put("domains", domainService.getAllAvailableDomain());
		modeMap.put("gamePages",gamePageService.getAllAvailableGamePage());
		ModelAndView view = new ModelAndView();
		view.setViewName("operative/game/peoluckywheel/user_act_analysis.main");
		return view;
	}
	
	@RequestMapping(value="/chart")
	@ResponseBody
	@RequiresPermissions(value={"peoluckywheel:useractals"})
	public String getChart(String domainId,String channelId,String gameId,String startDate,String endDate){
		String result = "";
		if (startDate.endsWith(endDate)) {
			TreeSet<String> scales = HourListUtil.getHourListPerHour();
			result = alPeopleStatHourlyService.getChart(domainId, channelId, gameId, startDate, scales).toString();
		}else{
			TreeSet<String> scales = DateListUtil.getCountDateList(startDate, endDate);
			result = alPeopleStatDailyService.getChart(domainId, channelId, gameId, startDate, endDate,scales).toString();
		}
		return result;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	@RequiresPermissions(value={"peoluckywheel:useractals"})
	public String getList(String domainId,String channelId,String gameId,String startDate,String endDate,int pageNumber,int pageSize){
		String result = alPeopleStatDailyService.find(domainId, channelId, gameId, startDate, endDate, pageNumber, pageSize).toString();
		return result;
	}
	
	@RequestMapping(value="/getChannels",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	@RequiresPermissions(value={"peoluckywheel:useractals"})
	public String getChannels(String domainId){
		String result = channelService.getAllAvailableChannelByDomain(domainId).toString();
		return result;
	}
}
