package com.hhly.smartdata.util;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Iritchie.ren on 2017/9/21.
 */
public class Result implements Serializable{
    /**
     * 返回状态码
     */
    private int status = ResultCode.OK;
    /**
     * 返回提示信息
     */
    private String message = ResultCode.getMsg(ResultCode.OK);
    /**
     * 返回数据
     */
    private Object data;

    public Result(){
        super();
    }

    public static Result success(){
        return success("");
    }

    public static Result success(Object data){
        Result result = new Result();
        result.setStatus(ResultCode.OK);
        result.setMessage(ResultCode.getMsg(ResultCode.OK));
        result.setData(data);
        return result;
    }

    public static Result fail(){
        return fail("");
    }

    public static Result fail(String msg){
        Result result = new Result();
        result.setStatus(ResultCode.ERROR_CODE);
        result.setMessage(ResultCode.getMsg(ResultCode.ERROR_CODE));
        result.setData(msg);
        return result;
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

    public static void send(){
    }
}
