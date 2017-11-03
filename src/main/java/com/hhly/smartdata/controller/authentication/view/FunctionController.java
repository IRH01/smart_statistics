package com.hhly.smartdata.controller.authentication.view;

import com.google.common.collect.Maps;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.service.authentication.FunctionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Iritchie.ren on 2017/9/22.
 */
@Controller
@RequestMapping("/sys/func")
public class FunctionController extends BaseController{
    @Autowired
    private FunctionService functionService;

    @RequestMapping("/list")
    @RequiresPermissions("!sys_func_list")
    public ModelAndView list(){
        Map<String, Object> model = Maps.newHashMap();
        try{
            model.put("funcList", functionService.getAll());
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }
        return new ModelAndView("system/function/list", model);
    }
}
