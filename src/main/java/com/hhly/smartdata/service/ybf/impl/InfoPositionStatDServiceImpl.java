package com.hhly.smartdata.service.ybf.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.dto.InfoByPosition;
import com.hhly.smartdata.dto.PositionByInfo;
import com.hhly.smartdata.mapper.ybf.DimPositionRepository;
import com.hhly.smartdata.mapper.ybf.InfoPositionStatDRepository;
import com.hhly.smartdata.service.ybf.InfoPositionStatDService;

@Service
public class InfoPositionStatDServiceImpl implements InfoPositionStatDService {

	@Autowired
	InfoPositionStatDRepository infoPositionStatDRepository;
	@Autowired
	DimPositionRepository dimPositionRepository;

	@Override
	public JSONObject getInfoPositionStatDByInfo(String domain, String date, String infoId) {

		Map<String, String> param = new HashMap<String, String>();
		param.put("domain", domain);
		param.put("date", date);
		param.put("infoId", infoId);

		List<PositionByInfo> positionByInfos = infoPositionStatDRepository.getInfoPositionStatDByInfo(param);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("positionByInfos", positionByInfos);

		return JSONObject.fromObject(resultMap);
	}
	
	/**
	 * 处理位置名称，拼接父节点进行显示
	 * @param domainId
	 * 			域名id
	 * @param positionStatDs
	 * @param seperaString
	 * 			分隔符
	 */
	@SuppressWarnings("unused")
	private void dealPosName(String domainId,List<PositionByInfo> positionStatDs,String seperaString){
		if(!CollectionUtils.isEmpty(positionStatDs)){
			List<Node> posNodes = dimPositionRepository.getPositions(domainId);
			if(CollectionUtils.isEmpty(posNodes)){
				return;
			}
			Map<String, Node> nodeMap = new HashMap<String, Node>();
			for(Node node : posNodes){
				nodeMap.put(node.getId(), node);
			}
			
			for(PositionByInfo pos: positionStatDs){
				List<String> posNameList = new ArrayList<String>();
				String posId = pos.getPositionId();
				Node posNode = nodeMap.get(posId);
				//遍历获取父节点
				while(null != posNode){
					posNameList.add(posNode.getName());
					posNode = nodeMap.get(posNode.getpId());
				}
				
				StringBuilder sBuilder =  new StringBuilder();
				for(int i = posNameList.size() - 1;i >= 0;i--){
					if(sBuilder.length() > 0){
						//添加分隔符
						sBuilder.append(seperaString);
					}
					//拼接父节点名称
					sBuilder.append(posNameList.get(i));
				}
				pos.setPositionName(sBuilder.toString());
			}
		}
	}

	@Override
	public JSONObject getInfoPositionStatDByPosition(String domain, String date, String positionId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain", domain);
		param.put("date", date);
		param.put("positionId", positionId);

		List<InfoByPosition> infoByPositions = infoPositionStatDRepository.getInfoPositionStatDByPosition(param);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("infoByPositions", infoByPositions);

		return JSONObject.fromObject(resultMap);

	}

}
