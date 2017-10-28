package com.hhly.smartdata.controller.authentication.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class UserControllerApi extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping("/verifyPasswd")
    @ResponseBody
    public boolean verifyPasswd(@ModelAttribute User user){
        User oldUser = null;
        try{
            oldUser = userService.getUser(user.getId());
        }catch(Exception e){
            LOGGER.error("操作失败" + e.getMessage());
        }
        return oldUser.getPasswd().equals(new Md5Hash(user.getPasswd()).toString());
    }
}
