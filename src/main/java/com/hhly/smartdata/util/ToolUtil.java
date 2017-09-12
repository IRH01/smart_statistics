package com.hhly.smartdata.util;

import com.hhly.smartdata.constant.Constants;

/**
 * 常用工具类
 * @author Administrator
 *
 */
public class ToolUtil {
	
	/**
	 * Integer转换为int(null 变为0)
	 * @param value
	 * @return
	 */
	public static int convertToInt(Integer value){
		if(null == value){
			return 0;
		}
		return value;
	}
	
	/**
	 * Integer转换为int(null 变为0)
	 * @param value
	 * @return
	 */
	public static long convertToLong(Long value){
		if(null == value){
			return 0;
		}
		return value;
	}
	
	public static String objectToString(Object value){
		if(null == value){
			return "";
		}
		return String.valueOf(value);
	}
	
	public static String getCurrencyUnit(String country){
		String currencyUnit = "";
		if(Constants.COUNTRY_CN.equals(country)){
			currencyUnit = "RMB";
		}else if(Constants.COUNTRY_MY.equals(country)){
			currencyUnit = "MYR";
		}else if(Constants.COUNTRY_SG.equals(country)){
			currencyUnit = "SGD";
		}else if(Constants.COUNTRY_TH.equals(country)){
			currencyUnit = "THB";
		}else if(Constants.COUNTRY_VN.equals(country)){
			currencyUnit = "VND";
		}
		return currencyUnit;
	}
}
