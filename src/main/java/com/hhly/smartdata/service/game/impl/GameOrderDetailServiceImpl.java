package com.hhly.smartdata.service.game.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.constant.FileConstants;
import com.hhly.smartdata.model.game.operative.GameOrderDetail;
import com.hhly.smartdata.mapper.game.operative.GameOrderDetailReposity;
import com.hhly.smartdata.service.game.GameOrderDetailService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;

@Service
public class GameOrderDetailServiceImpl implements GameOrderDetailService {

	@Autowired
	GameOrderDetailReposity gameOrderDetailReposity;
	
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<GameOrderDetail> values = gameOrderDetailReposity.find(conditionMap);
		PageInfo<GameOrderDetail> pageInfo = new PageInfo<GameOrderDetail>(values);
		return JSONObject.fromObject(pageInfo);
	}

	@Override
	public List<GameOrderDetail> getStates(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return gameOrderDetailReposity.getStates(condition);
	}

	@Override
	public List<GameOrderDetail> getProduces(Map<String, Object> condition) {
		// TODO Auto-generated method stub
		return gameOrderDetailReposity.getProduces(condition);
	}

	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean canExport(Map<String, Object> conditionMap) {
		int count = gameOrderDetailReposity.findCount(conditionMap);
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
		int count = gameOrderDetailReposity.findCount(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "订单列表";
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
				List<GameOrderDetail> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(getQValueList(valueList.get(i)));
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
		List<GameOrderDetail> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(getQValueList(valueList.get(i)));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
		return excelFile.getPath();
	}
	
	private List<String> getQTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("订单号");
		listColumn.add("订单类型");
		listColumn.add("产品");
		listColumn.add("服务器");
		listColumn.add("订单金额");
		listColumn.add("下单时间");
		listColumn.add("充值时间");
		listColumn.add("订单状态");
		listColumn.add("充值方式");
		listColumn.add("用户ID");
		listColumn.add("所属渠道ID");
		listColumn.add("交易平台");
		return listColumn;
	}
	
	private List<Object> getQValueList(GameOrderDetail value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getPaymentDrderno());
		valueList.add(value.getOrderType());
		valueList.add(value.getPlatformId());
		valueList.add(value.getServerId());
		valueList.add(value.getAmount());
		valueList.add(value.getCreateTime());
		valueList.add(value.getPayTime());
		valueList.add(value.getPayStatus());
		valueList.add(value.getRechargeWay());
		valueList.add(value.getUserId());
		valueList.add(value.getChannelId());
		valueList.add(value.getName());
		return valueList;
	}
	
	private List<GameOrderDetail> findByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<GameOrderDetail> values = gameOrderDetailReposity.find(conditionMap);
		return values;
	}
	
	
	
}
