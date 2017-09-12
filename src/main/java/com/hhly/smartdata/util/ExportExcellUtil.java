package com.hhly.smartdata.util;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ExportExcellUtil{

    /**
     * @param response     http请求返回
     * @param fileName     文件名
     * @param sheetName    sheet名字
     * @param titleColumns 每一列的名字集合
     * @param infos        数据的信息（二维数组）
     * @return
     */
    public static boolean export(HttpServletResponse response, String fileName, String sheetName, List<String> titleColumns,
                                 List<List<Object>> infos){

        OutputStream os = null;
        try{
            os = response.getOutputStream();
            response.reset();// 清空输出流
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("gbk"), "iso-8859-1") + ".xls");
            WritableWorkbook wbook = Workbook.createWorkbook(os); // 建立excel文件
            String tmptitle = sheetName; // 标题
            WritableSheet wsheet = wbook.createSheet(tmptitle, 0); // sheet名称

            WritableFont wfont = new WritableFont(WritableFont.ARIAL, 16, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            WritableCellFormat wcfFC = new WritableCellFormat(wfont);
            try{
                wcfFC.setBackground(Colour.AQUA);
            }catch(WriteException e){
                e.printStackTrace();
            }

            wfont = new jxl.write.WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
            wcfFC = new WritableCellFormat(wfont);

            try{
                // 添加titleColumn
                for(int i = 0; i < titleColumns.size(); i++){
                    wsheet.addCell(new Label(i, 0, titleColumns.get(i)));
                }
                // 添加数据
                for(int j = 0; j < infos.size(); j++){
                    for(int k = 0; k < infos.get(j).size(); k++){
                        wsheet.addCell(new Label(k, j + 1, String.valueOf(infos.get(j).get(k))));
                    }
                }

            }catch(RowsExceededException e){
                e.printStackTrace();
            }catch(WriteException e){
                e.printStackTrace();
            }

            wbook.write(); // 写入文件
            try{
                wbook.close();
                os.flush();
                os.close();
            }catch(WriteException e){
                e.printStackTrace();
            }

        }catch(IOException e){
            e.printStackTrace();
        } // 取得输出流

        return true;
    }

    /**
     * 生成excel文件
     *
     * @param filePath
     * @param sheetName
     * @param titleColumns
     * @param infos
     * @return
     */
    public static File createExcelFile(String filePath, String sheetName, List<String> titleColumns,
                                       List<List<Object>> infos, int columnWidth){
        try{
            File file = FileUtil.createFile(filePath);
            if(null == file){
                return null;
            }
            // 建立excel文件
            WritableWorkbook wbook = Workbook.createWorkbook(file);
            // 设置标题
            String tmptitle = sheetName;
            // 设置sheet名称
            WritableSheet wsheet = wbook.createSheet(tmptitle, 0);
            // 设置字体样式等
            WritableFont columnWfont = new WritableFont(WritableFont.ARIAL, 16,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);
            WritableFont valueWfont = new WritableFont(WritableFont.ARIAL, 14,
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);
            WritableCellFormat columnWcfFC = null;
            WritableCellFormat valueWcfFC = null;
            try{
                columnWcfFC = getCellFormat(columnWfont, Colour.BLUE_GREY, Alignment.CENTRE, VerticalAlignment.CENTRE, true, true);
            }catch(WriteException e1){
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try{
                valueWcfFC = getCellFormat(valueWfont, null, Alignment.CENTRE, VerticalAlignment.CENTRE, true, false);
            }catch(WriteException e1){
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


            try{
                // 添加titleColumn
                for(int i = 0; i < titleColumns.size(); i++){
                    wsheet.setColumnView(i, columnWidth);
                    wsheet.addCell(new Label(i, 0, titleColumns.get(i), columnWcfFC));
                }
                // 添加数据
                for(int j = 0; j < infos.size(); j++){
                    for(int k = 0; k < infos.get(j).size(); k++){
                        wsheet.addCell(new Label(k, j + 1, ToolUtil.objectToString(infos
                                .get(j).get(k)), valueWcfFC));
                    }
                }
            }catch(RowsExceededException e){
                e.printStackTrace();
            }catch(WriteException e){
                e.printStackTrace();
            }

            wbook.write(); // 写入文件
            try{
                wbook.close();
            }catch(WriteException e){
                e.printStackTrace();
            }
            return file;
        }catch(IOException e){
            e.printStackTrace();
        } // 取得输出流
        return null;
    }

    public static WritableCellFormat getCellFormat(WritableFont font, Colour backColor, Alignment alignment, VerticalAlignment verticalAlignment, boolean isWrap, boolean isLock) throws WriteException{
        WritableCellFormat cellFormat = new WritableCellFormat(font);
        if(null != backColor){
            cellFormat.setBackground(backColor);
        }
        if(null != alignment){
            cellFormat.setAlignment(alignment);
        }
        if(null != verticalAlignment){
            cellFormat.setVerticalAlignment(verticalAlignment);
        }
        cellFormat.setWrap(isWrap);
        cellFormat.setLocked(isLock);
        return cellFormat;
    }
}
