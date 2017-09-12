package com.hhly.smartdata.service.ybf.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.dto.PositionCountByDay;
import com.hhly.smartdata.dto.PositionStatDShower;
import com.hhly.smartdata.mapper.ybf.DimPositionRepository;
import com.hhly.smartdata.mapper.ybf.PositionStatDRepository;
import com.hhly.smartdata.service.ybf.PositionStatDService;

@Service
public class PositionStatDServiceImpl implements PositionStatDService {

	@Autowired
	private PositionStatDRepository positionStatDRepository;
	@Autowired
	DimPositionRepository dimPositionRepository;

	@Override
	public JSONObject getPositionStatD(String domain, String date, String positionIds, int pageNumber, int pageSize) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain", domain);
		param.put("date", date);

		String[] positionIdsArr = positionIds.split(",");

		if (positionIdsArr.length < 1) {
			param.put("positionIdArr", null);
		} else {
			param.put("positionIdArr", positionIdsArr);
		}

		// 分页查询
		PageHelper.startPage(pageNumber, pageSize);
		List<PositionStatDShower> positionStatDs = this.positionStatDRepository.getPositionStatD(param);
		PageInfo<PositionStatDShower> pageInfo = new PageInfo<PositionStatDShower>(positionStatDs);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("positionStatDs", pageInfo);
		resultMap.put("pageNumber", pageNumber);

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
	private void dealPosName(String domainId,List<PositionStatDShower> positionStatDs,String seperaString){
		if(!CollectionUtils.isEmpty(positionStatDs)){
			List<Node> posNodes = dimPositionRepository.getPositions(domainId);
			if(CollectionUtils.isEmpty(posNodes)){
				return;
			}
			Map<String, Node> nodeMap = new HashMap<String, Node>();
			for(Node node : posNodes){
				nodeMap.put(node.getId(), node);
			}
			
			for(PositionStatDShower pos: positionStatDs){
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
	public List<PositionStatDShower> getPositionStatDAll(String domain, String date, String positionIds) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain", domain);
		param.put("date", date);

		String[] positionIdsArr = positionIds.split(",");

		if (positionIdsArr.length < 1) {
			param.put("positionIdArr", null);
		} else {
			param.put("positionIdArr", positionIdsArr);
		}

		List<PositionStatDShower> positionStatDs = this.positionStatDRepository.getPositionStatD(param);
		
		return positionStatDs;
	}

	@Override
	public JSONObject getPositionStatDCountByDay(String domain, String date, String positionIds) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("domain", domain);
		param.put("date", date);

		String[] positionIdsArr = positionIds.split(",");

		if (positionIdsArr.length < 1) {
			param.put("positionIdArr", null);
		} else {
			param.put("positionIdArr", positionIdsArr);
		}

		PositionCountByDay positionCountByDay = positionStatDRepository.getPositionStatDCountByDay(param);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("positionCountByDay", positionCountByDay);

		return JSONObject.fromObject(resultMap);
	}

}
