package com.vy12021.framework.sysmgmt.security.controller;

import com.vy12021.framework.sysmgmt.security.model.User;
import com.vy12021.framework.sysmgmt.security.service.impl.UserServiceImpl;
import com.vy12021.framework.util.paging.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by LIUYONG on 14-2-26.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Pager pager) {
        pager.setPageSize(4);
        pager.setPageNumber(2);
        List<User> userList = userService.findPager(pager);
        return "a";
    }
}
