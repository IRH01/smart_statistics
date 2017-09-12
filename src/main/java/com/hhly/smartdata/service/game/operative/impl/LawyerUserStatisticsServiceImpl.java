package com.hhly.smartdata.service.game.operative.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.constant.FileConstants;
import com.hhly.smartdata.mapper.game.LawyerUserStatisticsReposity;
import com.hhly.smartdata.model.game.ChannelMonthAllbet;
import com.hhly.smartdata.service.game.operative.LawyerUserStatisticsService;
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
public class LawyerUserStatisticsServiceImpl implements LawyerUserStatisticsService{
    @Autowired
    private LawyerUserStatisticsReposity lawyerUserStatisticsReposity;

    @Override
    public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
                           int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<ChannelMonthAllbet> values = lawyerUserStatisticsReposity.find(conditionMap);
        PageInfo<ChannelMonthAllbet> pageInfo = new PageInfo<ChannelMonthAllbet>(values);
        return JSONObject.fromObject(pageInfo);
    }

    @Override
    public JSONObject findMonth(Map<String, Object> conditionMap,
                                int pageNumber, int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<ChannelMonthAllbet> values = lawyerUserStatisticsReposity.findMonth(conditionMap);
        PageInfo<ChannelMonthAllbet> pageInfo = new PageInfo<ChannelMonthAllbet>(values);
        return JSONObject.fromObject(pageInfo);
    }

    @Override
    public JSONObject findDay(Map<String, Object> conditionMap, int pageNumber,
                              int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<ChannelMonthAllbet> values = lawyerUserStatisticsReposity.findDay(conditionMap);
        PageInfo<ChannelMonthAllbet> pageInfo = new PageInfo<ChannelMonthAllbet>(values);
        return JSONObject.fromObject(pageInfo);
    }

    @Override
    public int count(Map<String, Object> conditionMap){
        // TODO Auto-generated method stub
        return lawyerUserStatisticsReposity.findCount(conditionMap);
    }

    @Override
    public boolean canExport(Map<String, Object> conditionMap){
        int count = lawyerUserStatisticsReposity.findCount(conditionMap);
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
    public boolean canExportMonth(Map<String, Object> conditionMap){
        int count = lawyerUserStatisticsReposity.findCountMonth(conditionMap);
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
    public boolean canExportDay(Map<String, Object> conditionMap){
        int count = lawyerUserStatisticsReposity.findCountDay(conditionMap);
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

    public String export(Map<String, Object> conditionMap){
        int count = lawyerUserStatisticsReposity.findCount(conditionMap);
        int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
        if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
            pageCount++;
        }
        //分页数量不能超过最大文件数
        if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
            return null;
        }
        String fileName = "律师楼用户渠道充值消费汇总报表";
        //多个分页，导出为Zip文件
        if(pageCount > 1){
            return exportZip(fileName, pageCount, conditionMap, 0);
        }else{
            //单页，导出为excel文件
            return exportExcel(fileName, conditionMap, 0);
        }
    }

    public String exportMonth(Map<String, Object> conditionMap){
        int count = lawyerUserStatisticsReposity.findCountMonth(conditionMap);
        int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
        if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
            pageCount++;
        }
        //分页数量不能超过最大文件数
        if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
            return null;
        }
        String fileName = "渠道充值消费汇总报表";
        //多个分页，导出为Zip文件
        if(pageCount > 1){
            return exportZipMonth(fileName, pageCount, conditionMap, 0);
        }else{
            //单页，导出为excel文件
            return exportExcelMonth(fileName, conditionMap, 0);
        }
    }


    public String exportDay(Map<String, Object> conditionMap){
        int count = lawyerUserStatisticsReposity.findCountDay(conditionMap);
        int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
        if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
            pageCount++;
        }
        //分页数量不能超过最大文件数
        if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
            return null;
        }
        String fileName = "渠道充值消费日报表";
        //多个分页，导出为Zip文件
        if(pageCount > 1){
            return exportZipDay(fileName, pageCount, conditionMap, 0);
        }else{
            //单页，导出为excel文件
            return exportExcelDay(fileName, conditionMap, 0);
        }
    }


    private List<String> getQTableHeaderListDay(){
        List<String> listColumn = new ArrayList<String>();
        listColumn.add("统计日期");
        listColumn.add("渠道");
        listColumn.add("注册用户数");
        listColumn.add("激活用户数");
        listColumn.add("充值用户数");
        listColumn.add("充值金额");
        listColumn.add("德州投注用户数");
        listColumn.add("德州投注金额");
        listColumn.add("电竞投注用户数");
        listColumn.add("电竞投注金额");
        return listColumn;
    }

    private List<Object> getQValueListDay(ChannelMonthAllbet value){
        List<Object> valueList = new ArrayList<Object>();
        valueList.add(value.getRechargeDay());
        valueList.add(value.getChannelId());
        valueList.add(value.getRegistUsers());
        valueList.add(value.getActionUsers());
        valueList.add(value.getRechargeUsers());
        valueList.add(value.getRechargeAmount());
        valueList.add(value.getDzBetingUsers());
        valueList.add(value.getDzBetingAccSum());
        valueList.add(value.getDjBetingUsers());
        valueList.add(value.getDjBetingAccSum());
        return valueList;
    }

    /**
     * 导出为zip
     *
     * @param fileName
     * @param pageCount
     * @param conditionMap
     */
    private String exportExcelDay(String fileName, Map<String, Object> conditionMap, int type){
        try{
            //创建临时文件路径，确保可存储
            FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
        List<String> listColumn = getQTableHeaderListDay();
        List<ChannelMonthAllbet> valueList = this.findByPageDay(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER, type);
        List<List<Object>> listRow = new ArrayList<List<Object>>();
        for(int i = 0; i < valueList.size(); i++){
            listRow.add(getQValueListDay(valueList.get(i)));
        }
        String filePath = folderName + ".xls";
        //生成excel文件
        File excelFile = ExportExcellUtil.createExcelFile(filePath, fileName, listColumn, listRow, 23);
        return excelFile.getPath();
    }

    private List<ChannelMonthAllbet> findByPageDay(Map<String, Object> conditionMap, int pageNumber, int pageSize, int type){
        PageHelper.startPage(pageNumber, pageSize);
        List<ChannelMonthAllbet> values = lawyerUserStatisticsReposity.findDay(conditionMap);
        return values;
    }

    /**
     * 导出为zip
     *
     * @param fileName
     * @param pageCount
     * @param conditionMap
     * @param response
     */
    private String exportZipDay(String fileName, int pageCount, Map<String, Object> conditionMap, int type){
        try{
            String folderName = fileName + System.currentTimeMillis();
            File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
            if(null == fileDir){
                return null;
            }
            List<String> listColumn = getQTableHeaderListDay();
            for(int pageNumber = 1; pageNumber <= pageCount; pageNumber++){
                List<ChannelMonthAllbet> valueList = this.findByPageDay(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER, type);
                List<List<Object>> listRow = new ArrayList<List<Object>>();
                for(int i = 0; i < valueList.size(); i++){
                    listRow.add(getQValueListDay(valueList.get(i)));
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


    private List<String> getQTableHeaderListMonth(){
        List<String> listColumn = new ArrayList<String>();
        listColumn.add("统计月份");
        listColumn.add("渠道");
        listColumn.add("注册用户数");
        listColumn.add("激活用户数");
        listColumn.add("充值用户数");
        listColumn.add("充值金额");
        listColumn.add("德州投注用户数");
        listColumn.add("德州投注金额");
        listColumn.add("电竞投注用户数");
        listColumn.add("电竞投注金额");
        return listColumn;
    }

    private List<Object> getQValueListMonth(ChannelMonthAllbet value){
        List<Object> valueList = new ArrayList<Object>();
        valueList.add(value.getRechargeMonth());
        valueList.add(value.getChannelId());
        valueList.add(value.getRegistUsers());
        valueList.add(value.getActionUsers());
        valueList.add(value.getRechargeUsers());
        valueList.add(value.getRechargeAmount());
        valueList.add(value.getDzBetingUsers());
        valueList.add(value.getDzBetingAccSum());
        valueList.add(value.getDjBetingUsers());
        valueList.add(value.getDjBetingAccSum());
        return valueList;
    }

    /**
     * 导出为zip
     *
     * @param fileName
     * @param pageCount
     * @param conditionMap
     */
    private String exportExcelMonth(String fileName, Map<String, Object> conditionMap, int type){
        try{
            //创建临时文件路径，确保可存储
            FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
        List<String> listColumn = getQTableHeaderListMonth();
        List<ChannelMonthAllbet> valueList = this.findByPageMonth(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER, type);
        List<List<Object>> listRow = new ArrayList<List<Object>>();
        for(int i = 0; i < valueList.size(); i++){
            listRow.add(getQValueListMonth(valueList.get(i)));
        }
        String filePath = folderName + ".xls";
        //生成excel文件
        File excelFile = ExportExcellUtil.createExcelFile(filePath, fileName, listColumn, listRow, 23);
        return excelFile.getPath();
    }

    private List<ChannelMonthAllbet> findByPageMonth(Map<String, Object> conditionMap, int pageNumber, int pageSize, int type){
        PageHelper.startPage(pageNumber, pageSize);
        List<ChannelMonthAllbet> values = lawyerUserStatisticsReposity.findMonth(conditionMap);
        return values;
    }

    /**
     * 导出为zip
     *
     * @param fileName
     * @param pageCount
     * @param conditionMap
     * @param response
     */
    private String exportZipMonth(String fileName, int pageCount, Map<String, Object> conditionMap, int type){
        try{
            String folderName = fileName + System.currentTimeMillis();
            File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
            if(null == fileDir){
                return null;
            }
            List<String> listColumn = getQTableHeaderListMonth();
            for(int pageNumber = 1; pageNumber <= pageCount; pageNumber++){
                List<ChannelMonthAllbet> valueList = this.findByPageMonth(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER, type);
                List<List<Object>> listRow = new ArrayList<List<Object>>();
                for(int i = 0; i < valueList.size(); i++){
                    listRow.add(getQValueListMonth(valueList.get(i)));
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


    private List<String> getQTableHeaderList(){
        List<String> listColumn = new ArrayList<String>();
        listColumn.add("统计月份");
        listColumn.add("渠道");
        listColumn.add("注册用户数");
        listColumn.add("激活用户数");
        listColumn.add("充值用户数");
        listColumn.add("充值金额");
        listColumn.add("投注用户数");
        listColumn.add("投注金额");
        return listColumn;
    }

    private List<Object> getQValueList(ChannelMonthAllbet value){
        List<Object> valueList = new ArrayList<Object>();
        valueList.add(value.getRechargeMonth());
        valueList.add(value.getChannelId());
        valueList.add(value.getRegistUsers());
        valueList.add(value.getActionUsers());
        valueList.add(value.getRechargeUsers());
        valueList.add(value.getRechargeAmount());
        valueList.add(value.getBetingUsers());
        valueList.add(value.getBetingAccSum());
        return valueList;
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
        List<ChannelMonthAllbet> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER, type);
        List<List<Object>> listRow = new ArrayList<List<Object>>();
        for(int i = 0; i < valueList.size(); i++){
            listRow.add(getQValueList(valueList.get(i)));
        }
        String filePath = folderName + ".xls";
        //生成excel文件
        File excelFile = ExportExcellUtil.createExcelFile(filePath, fileName, listColumn, listRow, 23);
        return excelFile.getPath();
    }

    private List<ChannelMonthAllbet> findByPage(Map<String, Object> conditionMap, int pageNumber, int pageSize, int type){
        PageHelper.startPage(pageNumber, pageSize);
        List<ChannelMonthAllbet> values = lawyerUserStatisticsReposity.find(conditionMap);
        return values;
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
                List<ChannelMonthAllbet> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER, type);
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
}
