package com.hhly.smartdata.controller.authentication.view;

import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by Iritchie.ren on 2017/9/22.
 */
@Controller
@RequestMapping("/sys/user")
public class UserController{

    @Autowired
    private UserService userService;

    @RequestMapping("/modifyPasswd")
    public String modifyPasswd(@ModelAttribute User user, Model model){
        user.setPassword(new Md5Hash(user.getNewPassword()).toString());
        try{
            userService.update(user);
        }catch(Exception e){
            e.printStackTrace();
        }
        model.addAttribute("redirectUrl", "/welcome.do");
        model.addAttribute("msg", "密码修改成功！");
        return "success";
    }


    @RequestMapping("/editPasswd")
    public String editPasswd(){
        return "sys/user/edit_passwd.main";
    }

    @RequestMapping("/{random}/valid.do")
    public ModelAndView valid(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        Boolean b = true;
        String username = new String(request.getParameter("smart.username").getBytes("iso8859_1"), "UTF-8");
        User user = null;
        try{
            user = userService.getUserByUsername(username);
        }catch(Exception e){
            e.printStackTrace();
        }
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
