package com.vy12021.framework.sysmgmt.index.controller;

import com.vy12021.framework.Constant;
import com.vy12021.framework.sysmgmt.index.model.ActivateUser;
import com.vy12021.framework.sysmgmt.index.service.ActivateService;
import com.vy12021.framework.sysmgmt.security.model.User;
import com.vy12021.framework.sysmgmt.security.model.UserInfo;
import com.vy12021.framework.sysmgmt.security.service.UserInfoService;
import com.vy12021.framework.sysmgmt.security.service.UserService;
import com.vy12021.framework.util.common.FormatUtils;
import com.vy12021.framework.util.validatecode.ValidateService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by LIUYONG on 14-2-18.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivateService activityService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ValidateService validateService;

    @Autowired
    private FormatUtils formatUtils;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(HttpServletRequest request, Map<String,Object> map) {
        return "admin/create";
    }

    @RequestMapping(value = "/usernameValidate", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    public @ResponseBody String usernameValidate(HttpServletRequest request) {
        String username = request.getParameter("username");
        User user = userService.findByUsername(username);
        if(user==null) {
            return Constant.available;
        }
        return Constant.invalid;
    }

    @RequestMapping(value = "/emailValidate", method = RequestMethod.POST, produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String emailValidate(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("UserInfo.email");
        UserInfo userInfo = userInfoService.findByEmail(email);
        if(userInfo==null) {
            return Constant.available;
        }
        return Constant.invalid;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create (HttpServletRequest request, Map<String,Object> map, User user) {
        if(userService.findByUsername(user.getUsername()) != null) {
            map.put("msg", "用户名已被注册，请重新提交");
            return "admin/create";
        }
        user.setPassword(formatUtils.Md5(user.getPassword()));
        user.setActivateStatus("0");
        userService.save(user);
        user.getUserInfo().setUserId(user.getId());
        userInfoService.save(user.getUserInfo());
        userService.register(user);
        map.put("msg", "激活邮件已发出，请注意查收！");
        //如果邮件要重新发送需要传递用户
        map.put("userId", user.getId().toString());
        return "admin/create";
    }

    @RequestMapping(value = "/reSendEmail/{userId}", method = RequestMethod.GET)
    public String reSendEmail(HttpServletRequest request, @PathVariable String userId, Map<String,Object> map) {
        User user = userService.findById(Integer.parseInt(userId));
        if(user != null) {
            userService.register(user);
        }
        map.put("msg", "激活邮件已发出，请注意查收！");
        map.put("userId", userId);
        return "admin/create";
    }

    @RequestMapping(value = "/activate/{uuid}", method = {RequestMethod.GET, RequestMethod.POST})
    public String preActivity(HttpServletRequest request, Map<String,Object> map, @PathVariable String uuid, User user) {
        ActivateUser activateUser = activityService.findByActivateId(uuid);
        String msg = "";
        if(activateUser != null) {
            user = userService.findByIds(formatUtils.convertStringArray(activateUser.getUserId().toString().split(","))).get(0);
            if(user.getActivateStatus() != null && user.getActivateStatus().equals("0")) {
                user.setActivateStatus("1");
                msg = "激活成功";
            } else {
                msg = "此链接失效";
            }
            userService.update(user);
        }
        UserInfo userInfo = userInfoService.findByUserId(user.getId());
        map.put("msg", msg);
        map.put("userInfo", userInfo);
        map.put("user", user);

        return "admin/activate";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String preLogin(Map<String, Object> map) {
        map.put("loginMsg", (String)SecurityUtils.getSubject().getSession().getAttribute("loginMsg"));
        SecurityUtils.getSubject().getSession().removeAttribute("loginMsg");
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
        token.setPassword(formatUtils.Md5(user.getPassword()).toCharArray());
        if(request.getParameter("rememberMe") != null && !request.getParameter("rememberMe").equals("")) {
            boolean rememberMe = Boolean.valueOf(request.getParameter("rememberMe"));
            token.setRememberMe(rememberMe);
        }
        loginMsg = "登陆成功";
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            loginMsg = "未知账户";
            SecurityUtils.getSubject().getSession().setAttribute("loginMsg", loginMsg);
            return "redirect:/admin/login";
        } catch (IncorrectCredentialsException ice) {
            loginMsg = "密码错误";
            SecurityUtils.getSubject().getSession().setAttribute("loginMsg", loginMsg);
            return "redirect:/admin/login";
        } catch ( LockedAccountException lae ) {
            loginMsg = "账户锁定";
            SecurityUtils.getSubject().getSession().setAttribute("loginMsg", loginMsg);
            return "redirect:/admin/login";
        } catch ( ExcessiveAttemptsException eae ) {
            loginMsg = "";
            SecurityUtils.getSubject().getSession().setAttribute("loginMsg", loginMsg);
            return "redirect:/admin/login";
        } catch ( AuthenticationException ae ) {
            //unexpected error?
        }
        User u = userService.findByUsername(user.getUsername());
        map.put("u", u);
        map.put("loginMsg", loginMsg);
        return "redirect:/sysmgmt/index";
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
