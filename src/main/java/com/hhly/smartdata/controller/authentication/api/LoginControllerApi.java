package com.hhly.smartdata.controller.authentication.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginControllerApi extends BaseController{
    @Autowired
    private UserService userService;

    /*
     * 注册使用,存在不合法
     */
    @RequestMapping("/validateUserName")
    public boolean ValidateUserName(HttpServletRequest req){
        String usrName = req.getParameter("smart.username");
        User usr = null;
        try{
            usr = userService.getUserByUsername(usrName);
        }catch(Exception e){
            LOGGER.error("异常！");
        }
        if(usr == null){
            return true;
        }
        return false;
    }

    /*
     * 找回密码使用,存在合法,不存在非法
     */
    @RequestMapping("/validateUserNameEx")
    public boolean validateUserName(HttpServletRequest req){
        String usrName = req.getParameter("smart.username");
        User usr = null;
        try{
            usr = userService.getUserByUsername(usrName);
        }catch(Exception e){
            LOGGER.error("异常！");
        }
        if(usr == null){
            return false;
        }
        return true;
    }
}

