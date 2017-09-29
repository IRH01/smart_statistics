package com.hhly.smartdata.controller;

import com.alibaba.fastjson.JSONObject;
import com.hhly.smartdata.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController{
    protected final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    public void send(Result result){
        LOGGER.info(JSONObject.toJSONString(result));
    }
}
