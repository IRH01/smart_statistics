package com.hhly.smartdata.controller.authentication;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.AdminService;
import com.hhly.smartdata.service.authentication.UserService;

@Controller
@RequestMapping("/sys/user")
public class UserController extends BaseController{
    @Resource
    private UserService userService;
    @Resource
    private AdminService adminService;

    /**
     * 初始化密码
     */
    @RequestMapping("/initPwd")
    public @ResponseBody String initPwd(@ModelAttribute User user) {
        user.setPassword(new Md5Hash("123456").toString());
        userService.update(user);
        return "SUCCESS";
    }

    @RequestMapping("/modifyPasswd")
    public String modifyPasswd(@ModelAttribute User user,Model model){
        user.setPassword(new Md5Hash(user.getNewPassword()).toString());
        userService.update(user);
        model.addAttribute(REDIRECT_URL,"/welcome.do");
        model.addAttribute(MSG,"密码修改成功！");
        return SUCCESS;
    }
    @RequestMapping("/verifyPasswd")
    public @ResponseBody boolean verifyPasswd(@ModelAttribute User user){
        User oldUser = userService.getUser(user.getUserId());
        return oldUser.getPassword().equals(new Md5Hash(user.getPassword()).toString());
    }
    @RequestMapping("/editPasswd")
    public String editPasswd(){
        return "sys/user/edit_passwd.main";
    }


    @RequestMapping("/{random}/valid.do")
    public ModelAndView valid(HttpServletRequest request,    HttpServletResponse response) throws UnsupportedEncodingException {
         Boolean  b=true ;
        String username=new String(request.getParameter("db.username").getBytes("iso8859_1"), "UTF-8");
        User user=userService.getUserByUsername(username);
        if(user !=null){
            b=false;
        }
        try {
            PrintWriter out = response.getWriter();
            out.print(b);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
