package com.cyj.dubbo.controller;

import com.cyj.dubbo.model.User;
import com.cyj.dubbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserService userService2;

    @RequestMapping("/user")
    public String userDetail(Model model,
                             Integer id) {
        User user = userService.queryUserById(id);
        User user2 = userService2.queryUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("user2", user2);
        return "userDetail";
    }
}
