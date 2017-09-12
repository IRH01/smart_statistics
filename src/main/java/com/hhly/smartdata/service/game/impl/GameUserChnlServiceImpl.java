package com.hhly.smartdata.service.game.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.constant.FileConstants;
import com.hhly.smartdata.model.game.operative.GameUserChnl;
import com.hhly.smartdata.mapper.game.operative.GameUserChnlReposity;
import com.hhly.smartdata.service.game.GameUserChnlService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;

@Service
public class GameUserChnlServiceImpl implements GameUserChnlService {

	@Autowired
	GameUserChnlReposity gameUserChnlReposity;
	
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<GameUserChnl> values = gameUserChnlReposity.find(conditionMap);
		PageInfo<GameUserChnl> pageInfo = new PageInfo<GameUserChnl>(values);
		return JSONObject.fromObject(pageInfo);
	}

	@Override
	public List<GameUserChnl> getProduces(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return gameUserChnlReposity.getProduces(condition);
	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean canExport(Map<String, Object> conditionMap) {
		int count = gameUserChnlReposity.findCount(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return false;
		}
		return true;
	}
	
	@Override
	public String export(Map<String, Object> conditionMap) {
		int count = gameUserChnlReposity.findCount(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "用户分析";
		//多个分页，导出为Zip文件
		if(pageCount > 1){
			return exportZip(fileName,pageCount,conditionMap,0);
		}else{
		//单页，导出为excel文件
			return exportExcel(fileName,conditionMap,0);
		}
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 * @param response
	 */
	private String exportZip(String fileName,int pageCount,Map<String, Object> conditionMap,int type){
		try {
			String folderName = fileName + System.currentTimeMillis();
			File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
			if(null == fileDir){
				return null;
			}
			List<String> listColumn = getQTableHeaderList();
			for(int pageNumber = 1;pageNumber <= pageCount;pageNumber++){
				List<GameUserChnl> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(getQValueList(valueList.get(i), conditionMap.get("userType")));
				}
				String filePath = fileDir.getPath() + System.getProperty("file.separator") + fileName + pageNumber + ".xls"; 
				ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
			}
			File zipFile = FileUtil.zipDIR(fileDir.getPath(), FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName + ".zip");
			//打包完成，删除目录
			FileUtil.delete(fileDir.getPath());
			return zipFile.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 */
	private String exportExcel(String fileName,Map<String, Object> conditionMap,int type){
		try {
			//创建临时文件路径，确保可存储
			FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
		List<String> listColumn = getQTableHeaderList();
		List<GameUserChnl> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(getQValueList(valueList.get(i), conditionMap.get("userType")));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
		return excelFile.getPath();
	}
	
	private List<String> getQTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("时间");
		listColumn.add("用户指标");
		listColumn.add("渠道ID");
		listColumn.add("平台");
		listColumn.add("产品");
		return listColumn;
	}
	
	private List<Object> getQValueList(GameUserChnl value, Object userType){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getStatDate());
		String userNum = "";
		if("1".equals(userType)) {
			userNum = value.getRegUcnt();
		} else if("2".equals(userType)) {
			userNum = value.getRechargeUcnt();
		} else if("3".equals(userType)) {
			userNum = value.getActiveUcnt();
		} else if("4".equals(userType)) {
			userNum = value.getActiveRate();
		}
		valueList.add(userNum);
		valueList.add(value.getChannelId());
		valueList.add(value.getPlatformId());
		valueList.add(value.getOsName());
		return valueList;
	}
	
	private List<GameUserChnl> findByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<GameUserChnl> values = gameUserChnlReposity.find(conditionMap);
		return values;
	}

	
	
	
	@Override
	public JSONObject getChart(Map<String, Object> conditionMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Float> datas = new LinkedList<Float>();
		List<String> scaleList = new LinkedList<String>();
		
		PageHelper.startPage(0, Integer.MAX_VALUE);
		List<GameUserChnl> values = gameUserChnlReposity.find(conditionMap);
		
		TreeSet<String> scales = (TreeSet<String>)conditionMap.get("scales");
		Iterator<String> iterator = scales.iterator();
		
		String userType = (String)conditionMap.get("userType");
		String userName = "注册用户";
		while (iterator.hasNext()) {

			float data = 0.00f;

			String currentScale = iterator.next();
			for (GameUserChnl value : values) {
				if(conditionMap.get("type").equals("D")) {
					if (currentScale.equals(value.getStatDate())) {

						if("1".equals(userType)) {
							data = Float.parseFloat(value.getRegUcnt());
//							userName = "注册用户";
						} else if("2".equals(userType)) {
							data = Float.parseFloat(value.getRechargeUcnt());
//							userName = "充值用户";
						} else if("3".equals(userType)) {
							data = Float.parseFloat(value.getActiveUcnt());
//							userName = "活跃用户";
						} else if("4".equals(userType)) {
							data = Float.parseFloat(value.getActiveRate());
//							userName = "活跃率";
						}
					}
				} 
				if(conditionMap.get("type").equals("H")) {
					if (currentScale.substring(0, 2).equals(value.getStatDate().substring(11, 13))) {
						if("1".equals(userType)) {
							data = Float.parseFloat(value.getRegUcnt());
//							userName = "注册用户";
						} else if("2".equals(userType)) {
							data = Float.parseFloat(value.getRechargeUcnt());
//							userName = "充值用户";
						} else if("3".equals(userType)) {
							data = Float.parseFloat(value.getActiveUcnt());
//							userName = "活跃用户";
						} else if("4".equals(userType)) {
							data = Float.parseFloat(value.getActiveRate());
//							userName = "活跃率";
						}
					}
				}
			}
			datas.add(data);
			scaleList.add(currentScale);
		}
		result.put("scales", scaleList);
		result.put("datas", datas);
		result.put("userType", userType);
		return JSONObject.fromObject(result);
	}
}
