package com.hhly.smartdata.util;

import java.io.Serializable;

/**
 * Created by Iritchie.ren on 2017/9/21.
 */
public class Result implements Serializable {
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

    public Result() {
        super();
    }

    public static Result success() {
        return success("");
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setStatus(ResultCode.OK);
        result.setMessage(ResultCode.getMsg(ResultCode.OK));
        result.setData(data);
        return result;
    }

    public static Result fail() {
        return fail("");
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.setStatus(ResultCode.ERROR_CODE);
        result.setMessage(ResultCode.getMsg(ResultCode.ERROR_CODE));
        result.setData(msg);
        return result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
