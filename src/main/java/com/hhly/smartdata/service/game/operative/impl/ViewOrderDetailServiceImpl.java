package com.hhly.smartdata.service.game.operative.impl;

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
import com.hhly.smartdata.model.game.operative.ViewOrderDetail;
import com.hhly.smartdata.mapper.game.operative.ViewOrderDetailReposity;
import com.hhly.smartdata.service.game.operative.ViewOrderDetailService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;

@Service
public class ViewOrderDetailServiceImpl implements ViewOrderDetailService {
	@Autowired
	ViewOrderDetailReposity viewOrderDetailReposity;
	
	@Override
	public JSONObject find(Map<String, Object> conditionMap,int pageNumber,int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ViewOrderDetail> values = viewOrderDetailReposity.find(conditionMap);
		PageInfo<ViewOrderDetail> pageInfo = new PageInfo<ViewOrderDetail>(values);
		return JSONObject.fromObject(pageInfo);
	}
	
	public boolean canExport(Map<String, Object> conditionMap){
		int count = viewOrderDetailReposity.count(conditionMap);
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
	
	public String export(Map<String, Object> conditionMap){
		int count = viewOrderDetailReposity.count(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "订单详情汇总";
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
		List<String> listColumn = type==1 ? getQTableHeaderList() : getTableHeaderList();
		List<ViewOrderDetail> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(type == 1 ? getQValueList(valueList.get(i)) : getValueList(valueList.get(i)));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
		return excelFile.getPath();
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
			List<String> listColumn = type==1 ? getQTableHeaderList() : getTableHeaderList();
			for(int pageNumber = 1;pageNumber <= pageCount;pageNumber++){
				List<ViewOrderDetail> valueList = this.findByPage(conditionMap, pageNumber, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(type == 1 ? getQValueList(valueList.get(i)) : getValueList(valueList.get(i)));
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
	
	private List<ViewOrderDetail> findByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ViewOrderDetail> values = type == 1 ? viewOrderDetailReposity.findQlfMemberData(conditionMap) : viewOrderDetailReposity.find(conditionMap);
		return values;
	}
	
	private List<String> getTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("单号");
		listColumn.add("状态");
		listColumn.add("用户名");
		listColumn.add("用户昵称");
		listColumn.add("渠道号");
		listColumn.add("类型");
		listColumn.add("充值方式");
		listColumn.add("开发商");
		listColumn.add("国家");
		listColumn.add("平台");
		listColumn.add("游戏");
		listColumn.add("服务器");
		listColumn.add("订单金额");
		listColumn.add("下单时间");
		listColumn.add("充值时间");
		listColumn.add("订单号");
		listColumn.add("充值单号");
		return listColumn;
	}
	
	private List<Object> getValueList(ViewOrderDetail value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getOrderNo());
		valueList.add(value.getPayStatusName());
		valueList.add(value.getUserId());
		valueList.add(value.getNickName());
		valueList.add(value.getChannelId());
		valueList.add(value.getOrderTypeName());
		valueList.add(value.getRechargeWayName());
		valueList.add(value.getDevelopers());
		valueList.add(value.getCountryName());
		valueList.add(value.getPlatformTerminalName());
		valueList.add(value.getPlatformName());
		valueList.add(value.getServerName());
		valueList.add(value.getAmount());
		valueList.add(value.getCreateTime());
		valueList.add(value.getPayTime());
		valueList.add(value.getPaymentOrderNo());
		valueList.add(value.getTradeNo());
		return valueList;
	}

	@Override
	public JSONObject findQlfMemberData(
			Map<String, Object> conditionMap, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ViewOrderDetail> values = viewOrderDetailReposity.findQlfMemberData(conditionMap);
		PageInfo<ViewOrderDetail> pageInfo = new PageInfo<ViewOrderDetail>(values);
		return JSONObject.fromObject(pageInfo);
	}

	@Override
	public String exportQlfMemberData(Map<String, Object> conditionMap) {
		int count = viewOrderDetailReposity.countQlfMemberData(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "充值对账数据";
		//多个分页，导出为Zip文件
		if(pageCount > 1){
			return exportZip(fileName,pageCount,conditionMap,1);
		}else{
		//单页，导出为excel文件
			return exportExcel(fileName,conditionMap,1);
		}
	}

	@Override
	public boolean canExportQlfMemberData(Map<String, Object> conditionMap) {
		int count = viewOrderDetailReposity.countQlfMemberData(conditionMap);
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
	
	
	private List<String> getQTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("平台单号");
		listColumn.add("充值方式");
		listColumn.add("金额");
		listColumn.add("充值时间");
		listColumn.add("订单号");
		listColumn.add("充值单号");
		listColumn.add("渠道");
		return listColumn;
	}
	
	private List<Object> getQValueList(ViewOrderDetail value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getOrderNo());
		valueList.add(value.getRechargeWayName());
		valueList.add(value.getAmount());
		valueList.add(value.getPayTime());
		valueList.add(value.getPaymentOrderNo());
		valueList.add(value.getTradeNo());
		valueList.add(value.getChannelId());
		return valueList;
	}
}
