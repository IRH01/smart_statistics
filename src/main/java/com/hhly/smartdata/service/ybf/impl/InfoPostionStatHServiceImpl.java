package com.hhly.smartdata.service.ybf.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.ybf.InfoPositionStatH;
import com.hhly.smartdata.mapper.ybf.InfoPositionStatHRepository;
import com.hhly.smartdata.service.ybf.InfoPositionStatHService;

import net.sf.json.JSONObject;

@Service
public class InfoPostionStatHServiceImpl implements InfoPositionStatHService {

	@Autowired
	private InfoPositionStatHRepository infoPositionStatHRepository;

	@Override
	public JSONObject getInfoPositionStatHCount(String domain, String date, String positionIds, Set<String> scales) {

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain", domain);
		param.put("date", date);

		String[] positionIdsArr = positionIds.split(",");

		if (positionIdsArr.length < 1) {
			param.put("positionIdArr", null);
		} else {
			param.put("positionIdArr", positionIdsArr);
		}

		List<InfoPositionStatH> infoPositionStatHs = this.infoPositionStatHRepository.getInfoTypeStatH(param);

		// ip
		List<Long> ipList = new LinkedList<Long>();

		List<String> scaleList = new LinkedList<String>();
		// 咨询
		// List<Long> consultList = new LinkedList<Long>();
		// 停留时长
		List<Long> stayTimeList = new LinkedList<Long>();
		// 点击次数
		List<Long> clickList = new LinkedList<Long>();
		Iterator<String> iterator = scales.iterator();
		while (iterator.hasNext()) {

			long ipPerScale = 0;
			// long consultPerScale = 0;
			long stayTimePerScale = 0;
			long clickPerScale = 0;
			String currentScale = iterator.next();

			for (InfoPositionStatH infoPositionStatH : infoPositionStatHs) {

				String hh = simpleDateFormat.format(infoPositionStatH.getEtlDate());
				if (currentScale.substring(0, 2).equals(hh)) {
					ipPerScale += infoPositionStatH.getIpCnt();
					stayTimePerScale += infoPositionStatH.getStayCnt();
					clickPerScale += infoPositionStatH.getClickCnt();
				}
			}

			ipList.add(ipPerScale);
			stayTimeList.add(stayTimePerScale);
			clickList.add(clickPerScale);
			scaleList.add(currentScale);
		}

		result.put("scales", scaleList);
		result.put("ipList", ipList);
		result.put("clickList", clickList);
		result.put("stayTimeList", stayTimeList);

		return JSONObject.fromObject(result);
	}

}
