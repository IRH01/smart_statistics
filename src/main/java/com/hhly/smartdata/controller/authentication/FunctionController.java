package com.hhly.smartdata.controller.authentication;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Maps;
import com.hhly.smartdata.service.authentication.FunctionService;
import com.hhly.smartdata.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/sys/func")
public class FunctionController extends BaseController{

    @Autowired
    private FunctionService functionService;

    @RequestMapping("/list")
    @RequiresPermissions("!sys_func_list")
    public ModelAndView list(){
        Map<String, Object> model = Maps.newHashMap();
        model.put("funcList", functionService.getAll());
        return new ModelAndView("system/function/list", model);
    }

    @RequestMapping("/update")
    @RequiresPermissions("!sys_func_update")
    @ResponseBody
    public Result update(@RequestParam("funcTree") String funcTree){
        Map<String, Integer> result = functionService.batchUpdateFuncs(JSONArray.parseArray(funcTree), 0);
        return Result.success(result);
    }
}
