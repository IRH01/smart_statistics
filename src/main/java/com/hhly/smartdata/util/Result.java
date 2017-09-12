package com.hhly.smartdata.util;

import java.util.HashMap;
import java.util.Map;

public class Result{
    public static final int OK = 0;
    public static final int ERROR_SYSTEM = 1;
    public static final int ERROR_PARAMS = 2;
    public static final int ERROR_MSGID = 3;
    public static final int PROMOTION_MODE_POSITION_EXAIT = 4;
    public static final int QUALIFIED_EXAIT = 5;
    public static final int RACKBACK_EXAIT = 6;
    public static final int GAME_TYPE_EXAIT = 7;
    public static final int GAME_TYPE_NAME_EXIST = 8;
    public static final int USER_SETTLEMENT_EXIST = 9;
    public static final int PARTNER_USER_NOT_EXAIT = 10;
    public static final int WITHDRAWAL_APPLICATION_EXAIT = 11;
    public static final int PROMOTION_MODE_GAME_EXAIT = 12;
    public static final int PROMOTION_EXAIT = 13;
    public static final int PARTNER_USER_STATE_ERROR = 14;
    public static final int LOGIN_FAIL_ACCOUNT_OR_PASSWORD_ERROR = 15;
    public static final int LOGIN_FAIL_ACCOUNT_NOT_EXIST = 16;
    public static final int LOGIN_FAIL_ACCOUNT_NOT_ENABLE = 17;
    public static final int LOGIN_FAIL_ACCOUNT_UN_CHECK = 18;
    public static final int LOGIN_FAIL_ACCOUNT_CHECKED_NO = 19;
    public static final int PARTNER_USER_EXIST = 20;
    public static final int FUND_LOGIN_PASSWORD_SAME = 21;
    public static final int PARTNER_PHONE_EXIST = 22;
    public static final int PARTNER_EMAIL_EXIST = 23;
    private static Map<Integer, String> errorMsgs = new HashMap();

    static{
        errorMsgs.put(Integer.valueOf(0), "SUCESS");
        errorMsgs.put(Integer.valueOf(1), "系统错误");
        errorMsgs.put(Integer.valueOf(2), "传递参数有误");
        errorMsgs.put(Integer.valueOf(3), "没有对应的协议");
        errorMsgs.put(Integer.valueOf(4), "该位置已被定制");
        errorMsgs.put(Integer.valueOf(5), "同游戏类型同资格类型的数据已经存在");
        errorMsgs.put(Integer.valueOf(6), "相同配置项的返佣记录已经存在");
        errorMsgs.put(Integer.valueOf(7), "该游戏已经分类");
        errorMsgs.put(Integer.valueOf(8), "该平台该游戏类名已经存在");
        errorMsgs.put(Integer.valueOf(9), "用户结算方式已经存在");
        errorMsgs.put(Integer.valueOf(10), "代理用户不存在");
        errorMsgs.put(Integer.valueOf(11), "本月提款申请已经存在");
        errorMsgs.put(Integer.valueOf(12), "定制游戏已存在");
        errorMsgs.put(Integer.valueOf(13), "线下代理已存在");
        errorMsgs.put(Integer.valueOf(14), "代理用户状态不正确");
        errorMsgs.put(Integer.valueOf(15), "账号或密码错误");
        errorMsgs.put(Integer.valueOf(16), "账号不存在");
        errorMsgs.put(Integer.valueOf(17), "账号已被禁用");
        errorMsgs.put(Integer.valueOf(18), "账号正在审核中");
        errorMsgs.put(Integer.valueOf(19), "账号审核未通过");
        errorMsgs.put(Integer.valueOf(21), "资金密码不能登录密码相同");
        errorMsgs.put(Integer.valueOf(20), "代理账户已存在");
        errorMsgs.put(Integer.valueOf(22), "手机号已被注册");
        errorMsgs.put(Integer.valueOf(23), "邮箱已被注册");
    }

    public Result(){
    }

    public static String getErrorMsg(int key){
        return (String) errorMsgs.get(key);
    }
}