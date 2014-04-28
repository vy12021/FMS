package com.springapp.mvc.web.controller;

import com.framework.sysmgmt.security.model.User;
import com.framework.sysmgmt.security.service.UserService;
import com.framework.util.common.FormatUtils;
import com.framework.util.validatecode.ValidateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by LIUYONG on 14-2-18.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ValidateService validateService;

    @Autowired
    private UserService userService;

    @Autowired
    private FormatUtils formatUtils;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String preLogin(HttpServletRequest request) {
        return "admin/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, User user, Map<String,Object> map, @RequestParam("validatecode") String validatecode) {
        String loginMsg = "";
        Subject subject = SecurityUtils.getSubject();
        if(!validatecode.equalsIgnoreCase((String)(subject.getSession().getAttribute("validateCode")))) {
            loginMsg = "验证码错误";
            map.put("loginMsg", loginMsg);
            return "admin/login";
        }
        //validateService.removeImage((String)(subject.getSession().getAttribute("validateCode")));
        UsernamePasswordToken token = new UsernamePasswordToken();
        token.setUsername(user.getUsername());
        token.setPassword(user.getPassword().toCharArray());
        if(request.getParameter("rememberMe") != null && !request.getParameter("rememberMe").equals("")) {
            boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));
            token.setRememberMe(rememberMe);
        }
        loginMsg = "登陆成功";
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            loginMsg = "未知账户";
            map.put("loginMsg", loginMsg);
            return "admin/login";
        } catch (IncorrectCredentialsException ice) {
            loginMsg = "密码错误";
            map.put("loginMsg", loginMsg);
            return "admin/login";
        } catch ( LockedAccountException lae ) {
            loginMsg = "账户锁定";
            map.put("loginMsg", loginMsg);
            return "admin/login";
        } catch ( ExcessiveAttemptsException eae ) {
            loginMsg = "";
            map.put("loginMsg", loginMsg);
            return "admin/login";
        } catch ( AuthenticationException ae ) {
            //unexpected error?
        }
        User u = userService.findByUsername(user.getUsername());
        map.put("u", u);
        map.put("loginMsg", loginMsg);
        return "/admin/home";
    }

    @RequestMapping(value = "/validatecode", method = RequestMethod.POST)
    @ResponseBody
    public String getValidateCode(HttpServletRequest request, HttpServletResponse response) {
        String uri = "";
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        validateService.removeImage((String)session.getAttribute("validateCode"));
        try {
            uri = validateService.GetValidateCode(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uri;
    }

    /**
     * 浏览器关闭，标签页关闭，或者刷新都会触发此事件
     */
    @RequestMapping(value = "/removeValidateCode", method = RequestMethod.POST)
    public void removeValidateCode() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        validateService.removeImage((String)session.getAttribute("validateCode"));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/admin/login";
    }

    @RequestMapping(value = "/unauthorized", method = RequestMethod.GET)
    public String unauthorized(HttpServletRequest request) {
        return "admin/unauthorized";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Map<String, Object> map) {
        Subject currentUser = SecurityUtils.getSubject();
        map.put("rememberMe", currentUser.isRemembered());
        return "admin/home";
    }

}
