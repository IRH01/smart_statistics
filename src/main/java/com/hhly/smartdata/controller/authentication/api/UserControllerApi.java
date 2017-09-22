package com.hhly.smartdata.controller.authentication.api;

import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/sys/user")
public class UserControllerApi extends BaseController{

    @Autowired
    private UserService userService;

    /**
     * 初始化密码
     */
    @RequestMapping("/initPwd")
    @ResponseBody
    public String initPwd(@ModelAttribute User user){
        user.setPassword(new Md5Hash("123456").toString());
        userService.update(user);
        return "SUCCESS";
    }

    @RequestMapping("/modifyPasswd")
    public String modifyPasswd(@ModelAttribute User user, Model model){
        user.setPassword(new Md5Hash(user.getNewPassword()).toString());
        userService.update(user);
        model.addAttribute("redirectUrl", "/welcome.do");
        model.addAttribute("msg", "密码修改成功！");
        return "success";
    }

    @RequestMapping("/verifyPasswd")
    @ResponseBody
    public boolean verifyPasswd(@ModelAttribute User user){
        User oldUser = userService.getUser(user.getUserId());
        return oldUser.getPassword().equals(new Md5Hash(user.getPassword()).toString());
    }

    @RequestMapping("/editPasswd")
    public String editPasswd(){
        return "sys/user/edit_passwd.main";
    }

    @RequestMapping("/{random}/valid.do")
    public ModelAndView valid(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        Boolean b = true;
        String username = new String(request.getParameter("db.username").getBytes("iso8859_1"), "UTF-8");
        User user = userService.getUserByUsername(username);
        if(user != null){
            b = false;
        }
        try{
            PrintWriter out = response.getWriter();
            out.print(b);
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
