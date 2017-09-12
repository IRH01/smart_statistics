package com.hhly.smartdata.constant;

public class FileConstants{

    /**
     * Excel临时文件存储目录(空格转义需要处理)
     */
    public final static String EXCEL_TEMP_FILE_FOLDER = FileConstants.class.getClassLoader().getResource(System.getProperty("file.separator")).getPath().replaceAll("%20", " ") + "excelTempFile";


    /**
     * Excel单Sheet表数据个数
     */
    public final static int EXCEL_SINGLE_SHEET_ROW_NUMBER = 60000;

    /**
     * 文件最大限制为10个
     */
    public final static int EXCEL_LIMIT_FILE_SIZE = 10;

}
