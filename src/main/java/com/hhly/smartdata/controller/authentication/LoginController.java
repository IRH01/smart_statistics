package com.hhly.smartdata.controller.authentication;

import com.google.code.kaptcha.Producer;
import com.hhly.smartdata.controller.BaseController;
import com.hhly.smartdata.util.SysConstant;
import com.hhly.smartdata.util.exception.IncorrectCaptchaException;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.service.authentication.UserService;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.SecurityUtil;
import com.hhly.smartdata.util.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Date;

@Controller
public class LoginController extends BaseController{

    @Autowired
    private Producer captchaProducer;
    @Autowired
    private UserService userService;

    @Value("${ENCODEKEY}")
    private String key;

    @RequestMapping("/welcome.do")
    @RequiresPermissions("welcome")
    public String welcome(HttpServletRequest request, Model model){
        return "system/main";
    }

    @RequestMapping("/login.do")
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
        return "/login/login";
    }

    @RequestMapping("/forgetPassWord.do")
    public String forgetPassWord(Model model){
        return "/login/passwordget";
    }

    @RequestMapping("/validateResetPassWord.do")
    public String validateResetPassWord(HttpServletRequest req, Model model){
        try{
            String deUrl = req.getParameter(key);
            String decodeUrl;

            decodeUrl = SecurityUtil.decrypt(key, deUrl);

            if(decodeUrl.indexOf(",") < -1){
                return "errorDisable";
            }
            String nameAndTime[] = decodeUrl.split(",");
            String time = nameAndTime[1];
            Date date = DateUtil.string2Date(time, "yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            if(date.before(now)){
                return "errorDisable";
            }
            String enUserName = SecurityUtil.encrypt(key, nameAndTime[0]);
            model.addAttribute("db.username", enUserName);

            return "/login/resetPassword";
        }catch(Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    @RequestMapping("/resetPassWord.do")
    public String resetPassWord(@ModelAttribute User us, Model model) throws Exception{
        String deUserName = SecurityUtil.decrypt(key, us.getUsername());
        User usr = userService.getUserByUsername(deUserName);
        usr.setPassword(new Md5Hash(us.getPassword()).toString());
        userService.update(usr);
        return "/login/successReset";

    }

    /*
     * 注册使用,存在不合法
     */
    @RequestMapping("/validateUserName.do")
    @ResponseBody
    public boolean ValidateUserName(HttpServletRequest req, HttpServletResponse resp){
        String usrName = req.getParameter("db.username");
        User usr = userService.getUserByUsername(usrName);
        if(usr == null){
            return true;
        }
        return false;
    }

    /*
     * 找回密码使用,存在合法,不存在非法
     */
    @RequestMapping("/validateUserNameEx.do")
    @ResponseBody
    public boolean validateUserName(HttpServletRequest req, HttpServletResponse resp){
        String usrName = req.getParameter("db.username");
        User usr = userService.getUserByUsername(usrName);
        if(usr == null){
            return false;
        }
        return true;
    }

    @RequestMapping("/kaptchaImage.do")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception{
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

