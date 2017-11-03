package com.hhly.smartdata.util;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/11/1.
 */
public class JsonUtil{

    /**
     * json字符串转map结构
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, String> jsonStr2Map(String jsonStr){
        Map<String, String> resultMap = Maps.newHashMap();
        if(jsonStr != null){
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            for(String key : jsonObject.keySet()){
                resultMap.put(key, jsonObject.getString(key));
            }
        }
        return resultMap;
    }
}
