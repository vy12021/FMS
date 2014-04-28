package com.springapp.mvc.web.controller;

import com.springapp.mvc.model.ActivateUser;
import com.springapp.mvc.mybatis.service.ActivateService;
import com.framework.Constant;
import com.framework.sysmgmt.security.model.User;
import com.framework.sysmgmt.security.model.UserInfo;
import com.framework.sysmgmt.security.service.UserInfoService;
import com.framework.sysmgmt.security.service.UserService;
import com.framework.util.common.FormatUtils;
import com.framework.util.mail.SendThread;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by LIUYONG on 14-1-31.
 */

@Controller
@RequestMapping("/register")
public class OuterUserController {

    public static final String USERCREATE = "register/create";

    @Autowired
    private UserService userService;

    @Autowired
    private ActivateService activityService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private FormatUtils formatUtils;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(HttpServletRequest request, Map<String,Object> map) {
        return USERCREATE;
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
    public String create (HttpServletRequest request, Map<String,Object> map, User user, ActivateUser activateUser) {
        if(userService.findByUsername(user.getUsername()) != null) {
            map.put("msg", "用户名已被注册，请重新提交");
            return "register/create";
        }
        user.setActivateStatus("0");
        userService.save(user);
        user.getUserInfo().setUserId(user.getId());
        userInfoService.save(user.getUserInfo());
        new Thread(new SendThread(user, activateUser, Constant.bathPath, activityService)).start();
        map.put("msg", "激活邮件已发出，请注意查收！");
        //如果邮件要重新发送需要传递用户
        map.put("userId", user.getId().toString());
        return "register/create";
    }

    @RequestMapping(value = "/reSendEmail/{userId}", method = RequestMethod.GET)
    public String reSendEmail(HttpServletRequest request, @PathVariable String userId, Map<String,Object> map) {
        User user = userService.findById(Integer.parseInt(userId));
        ActivateUser activateUser = activityService.findByUserId(Integer.valueOf(userId));
        Subject currentUser = SecurityUtils.getSubject();
        String bathPath = (String)currentUser.getSession().getAttribute("bathPath");
        if(user != null) {
            new Thread(new SendThread(user, activateUser, bathPath, activityService)).start();
        }
        map.put("msg", "激活邮件已发出，请注意查收！");
        map.put("userId", userId);
        return "register/create";
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
        map.put("msg", msg);
        map.put("security", user);
        return "register/activate";
    }

}
