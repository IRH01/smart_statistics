package com.hhly.smartdata.util;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Iritchie.ren on 2017/9/21.
 */
public class Result implements Serializable{
    private static final String SUCCESS_MSG = "操作成功";

    private static int STATUS_OK = 200;
    private int status = STATUS_OK;
    private String message = SUCCESS_MSG;
    private Object data;

    public Result(){
        super();
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setStatus(ResultCode.OK);
        result.setMessage(SUCCESS_MSG);
        result.setData(data);
        return result;
    }

    public static String getSuccessMsg(){
        return SUCCESS_MSG;
    }

    public static int getStatusOk(){
        return STATUS_OK;
    }

    public static void setStatusOk(int statusOk){
        STATUS_OK = statusOk;
    }

    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data = data;
    }

    public static String getClientIp(HttpServletRequest request){
        if(request == null)
            return "";
        String ip = "";
        try{
            ip = request.getHeader("x-forwarded-for");
            System.setProperty("java.net.preferIPv4Stack", "true");
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
                ip = request.getRemoteAddr();
                if(ip.equals("127.0.0.1") || ip.equals("0:0:0:0:0:0:0:1")){
                    InetAddress inet = null;
                    try{
                        inet = InetAddress.getLocalHost();
                    }catch(UnknownHostException e){
                        e.printStackTrace();
                    }
                    ip = inet.getHostAddress();
                }
            }
            if(ip != null && ip.length() > 15){
                if(ip.indexOf(",") > 0){
                    ip = ip.substring(0, ip.indexOf(","));
                }
            }
        }catch(Exception e){
            // TODO: handle exception
        }
        return ip;
    }
}
