package com.hhly.smartdata.controller.authentication;

import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;
import com.hhly.smartdata.service.authentication.FunctionService;

@Controller
@RequestMapping("/sys/func")
public class FunctionController {

    @Resource
    private FunctionService functionService;

    @RequestMapping("/list")
    @RequiresPermissions("!sys_func_list")
    public ModelAndView list(){
        Map<String,Object> model = Maps.newHashMap();
        model.put("funcList",functionService.getAll());
        return new ModelAndView("sys/func/list.main",model);
    }


    @RequestMapping("/update")
    @RequiresPermissions("!sys_func_update")
    public @ResponseBody Map<String,Integer> update(@RequestParam("funcTree") String funcTree) {
        Map<String,Integer> result = functionService.batchUpdateFuncs(JSONArray.parseArray(funcTree),0);
        return result;
    }
}
