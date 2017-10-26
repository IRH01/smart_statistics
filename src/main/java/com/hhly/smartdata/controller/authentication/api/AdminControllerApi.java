package com.hhly.smartdata.controller.authentication.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.AdminService;
import com.hhly.smartdata.service.authentication.UserService;
import com.hhly.smartdata.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/admin")
public class AdminControllerApi extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    /**
     * 初始化密码
     */
    @RequestMapping("/initPwd")
    @RequiresPermissions("admin_admin_initPwd")
    public Result initPwd(@ModelAttribute User user){
        user.setPasswd(new Md5Hash("123456").toString());
        try{
            userService.update(user);
        }catch(Exception e){
            LOGGER.error("初始化密码异常！");
        }
        return Result.success(null);
    }

    /**
     * 删除用户
     */
    @RequestMapping("/del")
    public Result deleteUser(@RequestParam Integer userId){
        try{
            if(0 < adminService.deleteByUserId(userId)){
                return Result.success(null);
            }
        }catch(Exception e){
            LOGGER.error("删除用户异常！");
        }
        return Result.fail();
    }
}
