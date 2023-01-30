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

    @RequestMapping("/userDetail")
    public String userDetail(Model model,
                             Integer id) {
        User user = userService.queryUserById(id);
        model.addAttribute("user", user);
        return "userDetail";
    }
}
