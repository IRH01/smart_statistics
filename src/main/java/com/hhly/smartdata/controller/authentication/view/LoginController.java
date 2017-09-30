package com.hhly.smartdata.controller.authentication.view;

import com.google.code.kaptcha.Producer;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.util.SessionUtil;
import com.hhly.smartdata.util.SysConstant;
import com.hhly.smartdata.util.exception.IncorrectCaptchaException;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * Created by Iritchie.ren on 2017/9/22.
 */
@Controller
public class LoginController extends BaseController{

    @Autowired
    private Producer captchaProducer;

    @RequestMapping("/welcome.do")
    @RequiresPermissions("welcome")
    public String welcome(HttpServletRequest request, Model model){
        return "system/main";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp){
        //如果用户已登录,直接跳转欢迎页面
        Subject subject = SecurityUtils.getSubject();
        if(subject != null && subject.isAuthenticated()){
            return "redirect:welcome.do";
        }
        String errorClassName = (String) req.getAttribute("shiroLoginFailure");
        if(UnknownAccountException.class.getName().equals(errorClassName)){
            req.setAttribute("msg", "用户名或密码错误！");
        }else if(IncorrectCredentialsException.class.getName().equals(errorClassName)){
            req.setAttribute("msg", "用户名或密码错误！");
        }else if(DisabledAccountException.class.getName().equals(errorClassName)){
            req.setAttribute("msg", "该账户已被禁用！");
        }else if(IncorrectCaptchaException.class.getName().equals(errorClassName)){
            req.setAttribute("msg", "验证码输入错误！");
        }else if(errorClassName != null){
            req.setAttribute("msg", "系统异常,暂时无法登陆!");
        }
        return "system/login";
    }

    @RequestMapping("/kaptchaImage")
    public ModelAndView getKaptchaImage(HttpServletResponse response) throws Exception{
        //如果用户已登录,直接跳转欢迎页面
        Subject subject = SecurityUtils.getSubject();
        if(subject != null && subject.isAuthenticated()){
            subject.logout();
        }
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        SessionUtil.setSessionAttr(SysConstant.SESSION_VERIFY_CODE, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try{
            out.flush();
        }finally{
            out.close();
        }
        return null;
    }

}
