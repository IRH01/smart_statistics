package com.hhly.smartdata.service.game.operative.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.constant.FileConstants;
import com.hhly.smartdata.mapper.game.DebitGameReposity;
import com.hhly.smartdata.model.game.DebitGame;
import com.hhly.smartdata.service.game.operative.DebitGameService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DebitGameServiceImpl implements DebitGameService{
    @Autowired
    private DebitGameReposity debitGameReposity;

    @Override
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
                           int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<DebitGame> values = debitGameReposity.find(conditionMap);
        PageInfo<DebitGame> pageInfo = new PageInfo<DebitGame>(values);
        return JSONObject.fromObject(pageInfo);
    }

    @Override
    public boolean canExport(Map<String, Object> conditionMap){
        int count = debitGameReposity.findCount(conditionMap);
        int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
        if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
            pageCount++;
        }
        //分页数量不能超过最大文件数
        if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
            return false;
        }
        return true;
    }

    @Override
    public String export(Map<String, Object> conditionMap){
        int count = debitGameReposity.findCount(conditionMap);
        int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
        if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
            pageCount++;
        }
        //分页数量不能超过最大文件数
        if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
            return null;
        }
        String fileName = "游戏划账列表";
        //多个分页，导出为Zip文件
        if(pageCount > 1){
            return exportZip(fileName, pageCount, conditionMap, 0);
        }else{
            //单页，导出为excel文件
            return exportExcel(fileName, conditionMap, 0);
        }
    }

    /**
     * 导出为zip
     *
     * @param fileName
     * @param pageCount
     * @param conditionMap
     * @param response
     */
    private String exportZip(String fileName, int pageCount, Map<String, Object> conditionMap, int type){
        try{
            String folderName = fileName + System.currentTimeMillis();
            File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
            if(null == fileDir){
                return null;
            }
            List<String> listColumn = getQTableHeaderList();
            for(int pageNumber = 1; pageNumber <= pageCount; pageNumber++){
                List<DebitGame> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER, type);
                List<List<Object>> listRow = new ArrayList<List<Object>>();
                for(int i = 0; i < valueList.size(); i++){
                    listRow.add(getQValueList(valueList.get(i)));
                }
                String filePath = fileDir.getPath() + System.getProperty("file.separator") + fileName + pageNumber + ".xls";
                ExportExcellUtil.createExcelFile(filePath, fileName, listColumn, listRow, 23);
            }
            File zipFile = FileUtil.zipDIR(fileDir.getPath(), FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName + ".zip");
            //打包完成，删除目录
            FileUtil.delete(fileDir.getPath());
            return zipFile.getPath();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 导出为zip
     *
     * @param fileName
     * @param pageCount
     * @param conditionMap
     */
    private String exportExcel(String fileName, Map<String, Object> conditionMap, int type){
        try{
            //创建临时文件路径，确保可存储
            FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
        List<String> listColumn = getQTableHeaderList();
        List<DebitGame> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER, type);
        List<List<Object>> listRow = new ArrayList<List<Object>>();
        for(int i = 0; i < valueList.size(); i++){
            listRow.add(getQValueList(valueList.get(i)));
        }
        String filePath = folderName + ".xls";
        //生成excel文件
        File excelFile = ExportExcellUtil.createExcelFile(filePath, fileName, listColumn, listRow, 23);
        return excelFile.getPath();
    }

    private List<String> getQTableHeaderList(){
        List<String> listColumn = new ArrayList<String>();
        listColumn.add("订单号");
        listColumn.add("来源订单号");
        listColumn.add("目标订单号");
        listColumn.add("用户名");
        listColumn.add("用户昵称");
        listColumn.add("划账状态");
        listColumn.add("划出游戏应用");
        listColumn.add("划出金额");
        listColumn.add("划出游戏余额");
        listColumn.add("划账提交时间");
        listColumn.add("交易时间");
        listColumn.add("划入游戏应用");
        listColumn.add("游戏应用服务器");
        //listColumn.add("划入游戏余额");
        return listColumn;
    }

    private List<Object> getQValueList(DebitGame value){
        List<Object> valueList = new ArrayList<Object>();
        valueList.add(value.getOrderId());
        valueList.add(value.getFormOrder());
        valueList.add(value.getToOrder());
        valueList.add(value.getUserId());
        valueList.add(value.getNickName());
        valueList.add(value.getExchangeStatus());
        valueList.add(value.getExchangeGame());
        valueList.add(value.getExchangeAmount());
        valueList.add(value.getOverageAmount());
        valueList.add(value.getCommitTime());
        valueList.add(value.getTradeTime());
        valueList.add(value.getExchangeToGame());
        valueList.add(value.getServerId());
//		valueList.add(value.getExchangeToAmount());
        return valueList;
    }

    private List<DebitGame> findByPage(Map<String, Object> conditionMap, int pageNumber, int pageSize, int type){
        PageHelper.startPage(pageNumber, pageSize);
        List<DebitGame> values = debitGameReposity.find(conditionMap);
        return values;
    }
}
