package com.hhly.smartdata.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wy on 13-7-2.
 */
public class ResultCode {
    public static final int OK = 200;
    public static final int ERROR_CODE = 400;
    public static final int ERROR_USER = 401;
    public static final int ERROR_STATUS = 402;
    public static final int ERROR_DUPLICATE_ENTRY = 403;

    public static final int ERROR_SYSTEM = 500;

    public static Map<Integer, String> errorMsgs = new HashMap<Integer, String>();


    static {
        errorMsgs.put(OK, "操作成功");
        errorMsgs.put(ERROR_USER, "错误的用户,请输入正确的账号.");
        errorMsgs.put(ERROR_CODE, "错误的验证码,请输入正确的验证码.");
        errorMsgs.put(ERROR_STATUS, "错误的状态,请联系管理员.");
        errorMsgs.put(ERROR_DUPLICATE_ENTRY, "输入项重复,请检查输入项.");
        errorMsgs.put(ERROR_SYSTEM, "操作失败,请联系管理员.");
    }
}
