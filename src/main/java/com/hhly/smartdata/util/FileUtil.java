package com.hhly.smartdata.util;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Blob;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil{

    public final static Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
    public static Map<String, String> sizeMap = new HashMap<String, String>();

    static{
        getAllFileType();  //初始化文件类型信息
    }

    public static byte[] getBytesFromFile(File f){
        if(f == null){
            return null;
        }
        try{
            FileInputStream stream = new FileInputStream(f);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] input2byte(InputStream inStream)
            throws IOException{
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while((rc = inStream.read(buff, 0, 100)) > 0){
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

    /**
     * Created on 2010-7-1
     * <p>Discription:[getAllFileType,常见文件头信息]</p>
     */
    private static void getAllFileType(){
        FILE_TYPE_MAP.put("jpeg", "FFD8FF"); //JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47");  //PNG (png)
        FILE_TYPE_MAP.put("gif", "47494638");  //GIF (gif)
        FILE_TYPE_MAP.put("tif", "49492A00");  //TIFF (tif)
        FILE_TYPE_MAP.put("bmp", "424D"); //Windows Bitmap (bmp)
        FILE_TYPE_MAP.put("dwg", "41433130"); //CAD (dwg)
        FILE_TYPE_MAP.put("html", "68746D6C3E");  //HTML (html)
        FILE_TYPE_MAP.put("rtf", "7B5C727466");  //Rich Text Format (rtf)
        FILE_TYPE_MAP.put("xml", "3C3F786D6C");
        FILE_TYPE_MAP.put("zip", "504B0304");
        FILE_TYPE_MAP.put("rar", "52617221");
        FILE_TYPE_MAP.put("psd", "38425053");  //Photoshop (psd)
        FILE_TYPE_MAP.put("eml", "44656C69766572792D646174653A");  //Email [thorough only] (eml)
        FILE_TYPE_MAP.put("dbx", "CFAD12FEC5FD746F");  //Outlook Express (dbx)
        FILE_TYPE_MAP.put("pst", "2142444E");  //Outlook (pst)
        FILE_TYPE_MAP.put("xls", "D0CF11E0");  //MS Word
        FILE_TYPE_MAP.put("doc", "D0CF11E0");  //MS Excel 注意：word 和 excel的文件头一样
        FILE_TYPE_MAP.put("mdb", "5374616E64617264204A");  //MS Access (mdb)
        FILE_TYPE_MAP.put("wpd", "FF575043"); //WordPerfect (wpd)
        FILE_TYPE_MAP.put("eps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("ps", "252150532D41646F6265");
        FILE_TYPE_MAP.put("pdf", "255044462D312E");  //Adobe Acrobat (pdf)
        FILE_TYPE_MAP.put("qdf", "AC9EBD8F");  //Quicken (qdf)
        FILE_TYPE_MAP.put("pwl", "E3828596");  //Windows Password (pwl)
        FILE_TYPE_MAP.put("wav", "57415645");  //Wave (wav)
        FILE_TYPE_MAP.put("avi", "41564920");
        FILE_TYPE_MAP.put("ram", "2E7261FD");  //Real Audio (ram)
        FILE_TYPE_MAP.put("rm", "2E524D46");  //Real Media (rm)
        FILE_TYPE_MAP.put("mpg", "000001BA");  //
        FILE_TYPE_MAP.put("mov", "6D6F6F76");  //Quicktime (mov)
        FILE_TYPE_MAP.put("asf", "3026B2758E66CF11"); //Windows Media (asf)
        FILE_TYPE_MAP.put("mid", "4D546864");  //MIDI (mid)
    }

    /**
     * Created on 2010-7-1
     * <p>Discription:[getImageFileType,获取图片文件实际类型,若不是图片则返回null]</p>
     *
     * @return fileType
     */
    public static String getImageFileType(File f){
        if(isImage(f)){
            try{
                ImageInputStream iis = ImageIO.createImageInputStream(f);
                Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
                if(!iter.hasNext()){
                    return null;
                }
                ImageReader reader = iter.next();
                iis.close();
                return reader.getFormatName();
            }catch(IOException e){
                return null;
            }catch(Exception e){
                return null;
            }
        }
        return null;
    }

    /**
     * Created on 2010-7-1
     * <p>Discription:[getFileByFile,获取文件类型,包括图片,若格式不是已配置的,则返回null]</p>
     *
     * @return fileType
     */
    public static String getFileType(File file){
        String filetype = null;
        byte[] b = new byte[50];
        try{
            InputStream is = new FileInputStream(file);
            is.read(b);
            filetype = getFileTypeByStream(b);
            is.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return filetype;
    }

    /**
     * Created on 2010-7-1
     * <p>Discription:[getFileTypeByStream]</p>
     *
     * @return fileType
     */
    public static String getFileTypeByStream(byte[] b){
        String filetypeHex = String.valueOf(getFileHexString(b));
        for(Map.Entry<String, String> entry : FILE_TYPE_MAP.entrySet()){
            String fileTypeHexValue = entry.getValue();
            if(filetypeHex.toUpperCase().startsWith(fileTypeHexValue)){
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Created on 2010-7-2
     * <p>Discription:[isImage,判断文件是否为图片]</p>
     *
     * @return true 是 | false 否
     */
    public static boolean isImage(File file){
        boolean flag = false;
        try{
            BufferedImage bufreader = ImageIO.read(file);
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            flag = !(width == 0 || height == 0);
        }catch(IOException e){
            flag = false;
        }catch(Exception e){
            flag = false;
        }
        return flag;
    }

    /**
     * Created on 2010-7-1
     * <p>Discription:[getFileHexString]</p>
     *
     * @param b
     * @return fileTypeHex
     */
    public static String getFileHexString(byte[] b){
        StringBuilder stringBuilder = new StringBuilder();
        if(b == null || b.length <= 0){
            return null;
        }
        for(byte aB : b){
            int v = aB & 0xFF;
            String hv = Integer.toHexString(v);
            if(hv.length() < 2){
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(File file){
        byte[] buffer = null;
        try{
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while((n = fis.read(b)) != -1){
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return buffer;
    }

    //上传文件取文件扩展名
    public static String getExtension(String fileName){
        if(StringUtils.isEmpty(fileName))
            return "";
        else{
            int pos = fileName.lastIndexOf(".");
            return fileName.substring(pos + 1);
        }
    }

    public static String getImageSizeFromUrl(String urlString) throws IOException{
        if(urlString == null || urlString.trim().equals("")){
            return "";
        }
        String size = sizeMap.get(urlString);
        if(size != null) return size;
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        String result = "";
        try{
            BufferedImage bufreader = ImageIO.read(is);
            int width = bufreader.getWidth();
            int height = bufreader.getHeight();
            result = "" + width + "*" + height;
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            is.close();
        }
        sizeMap.put(urlString, result);
        return result;
    }

    public static byte[] blob2ByteArr(Blob blob) throws Exception{
        BufferedInputStream is = null;

        try{
            is = new BufferedInputStream(blob.getBinaryStream());
            byte[] bytes = new byte[(int) blob.length()];
            int len = bytes.length;
            int offset = 0;
            int read = 0;

            while(offset < len && (read = is.read(bytes, offset, len - offset)) >= 0){
                offset += read;
            }
            return bytes;
        }catch(Exception e){
            return null;
        }finally{
            try{
                is.close();
                is = null;
            }catch(IOException e){
                return null;
            }
        }
    }

    public static File createFile(String filePath) throws IOException{
        File file = new File(filePath);
        boolean result = file.createNewFile();
        //文件不存在，或者当前目录不是文件，则返回空
        if(!result || null == file || !file.isFile() || !file.exists()){
            return null;
        }
        return file;
    }

    public static File createDir(String dirPath) throws IOException{
        File file = new File(dirPath);
        if(file.exists() && file.isDirectory()){
            return file;
        }
        boolean result = file.mkdirs();
        //路径不存在，或者当前目录不是文件，则返回空
        if(!result || null == file || !file.isDirectory() || !file.exists()){
            return null;
        }
        return file;
    }

    private static String getExtName(String s, char split){
        int i = s.indexOf(split);
        int leg = s.length();
        return (i > 0 ? (i + 1) == leg ? " " : s.substring(i, s.length()) : " ");
    }

    /**
     * 将目录生成ZIP文件
     *
     * @param sourceDIR     文件夹名称（包含路径）
     * @param targetZipFile 生成zip文件名
     * @throws IOException
     */
    @SuppressWarnings("resource")
    public static File zipDIR(String sourceDIR, String targetZipFile) throws IOException{
        try{
            FileOutputStream target = new FileOutputStream(targetZipFile);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(target));
            int BUFFER_SIZE = 1024;
            byte buff[] = new byte[BUFFER_SIZE];
            File dir = new File(sourceDIR);
            if(!dir.isDirectory()){
                throw new IllegalArgumentException(sourceDIR + " is not a directory!");
            }
            File files[] = dir.listFiles();

            for(int i = 0; i < files.length; i++){
                FileInputStream fi = new FileInputStream(files[i]);
                BufferedInputStream origin = new BufferedInputStream(fi);
                ZipEntry entry = new ZipEntry(files[i].getName());
                out.putNextEntry(entry);
                int count;
                while((count = origin.read(buff)) != -1){
                    out.write(buff, 0, count);
                }
                origin.close();
            }
            out.close();
            return new File(targetZipFile);

        }catch(IOException e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 文件下载
     *
     * @param response
     * @param file
     */
    public static void downLoadFile(HttpServletResponse response, File file, String fileName){
        if(file == null || !file.exists()){
            return;
        }
        OutputStream out = null;
        try{
            //response.reset();
            //response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + new String(fileName.getBytes("gbk"), "iso-8859-1") + getExtName(file.getName(), '.'));
            out = response.getOutputStream();
            out.write(FileUtils.readFileToByteArray(file));
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(out != null){
                try{
                    out.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName){
        File file = new File(fileName);
        if(!file.exists()){
            return false;
        }else{
            if(file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName){
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if(file.exists() && file.isFile()){
            if(file.delete()){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir){
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if(!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if((!dirFile.exists()) || (!dirFile.isDirectory())){
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for(int i = 0; i < files.length; i++){
            // 删除子文件
            if(files[i].isFile()){
                flag = deleteFile(files[i].getAbsolutePath());
                if(!flag)
                    break;
            }
            // 删除子目录
            else if(files[i].isDirectory()){
                flag = deleteDirectory(files[i].getAbsolutePath());
                if(!flag)
                    break;
            }
        }
        if(!flag){
            return false;
        }
        // 删除当前目录
        if(dirFile.delete()){
            return true;
        }else{
            return false;
        }
    }

}
