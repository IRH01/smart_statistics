package com.hhly.smartdata.controller.authentication.api;

import com.alibaba.fastjson.JSONArray;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.authentication.FunctionService;
import com.hhly.smartdata.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/sys/func")
public class FunctionControllerApi extends BaseController{
    @Autowired
    private FunctionService functionService;

    @RequestMapping("/update")
    @RequiresPermissions("!sys_func_update")
    public Result update(@RequestParam("funcTree") String funcTree){
        Map<String, Integer> result = null;
        try{
            result = functionService.batchUpdateFuncs(JSONArray.parseArray(funcTree), 0);
        }catch(Exception e){
            LOGGER.error("更新异常！");
        }
        return Result.success(result);
    }
}
