package com.hhly.smartdata.dto;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * Controller调用返回结果
 * @author wanghuang
 *
 */
public class ControllerResult {
	public static final int OK = 0;

	public static final int ERROR_SYSTEM = 1;
	public static final int ERROR_PARAMS = 2;
	
	public static final int LOGIN_ERROR_ACCOUNT_OR_PASSWORD = 3;
	public static final int LOGIN_ERROR_CAPTCHA = 4;

	private static Map<Integer, String> errorMsgs = new HashMap<Integer, String>();

	static {
		errorMsgs.put(OK, "SUCESS");
		errorMsgs.put(ERROR_SYSTEM, "系统错误");
		errorMsgs.put(ERROR_PARAMS, "参数错误");
		errorMsgs.put(LOGIN_ERROR_ACCOUNT_OR_PASSWORD, "账号或密码错误");
		errorMsgs.put(LOGIN_ERROR_CAPTCHA, "验证码错误");
	}
	
	private int result;
	private String msg;
	private Object value;
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	
	public String getMsg() {
		if(StringUtils.isEmpty(msg)){
			return errorMsgs.get(result);
		}
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
