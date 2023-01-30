package com.cyj.dubbo.service;

import com.cyj.dubbo.model.User;

public class UserServiceImpl implements UserService {

    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("zhangsan");
        return user;
    }
}
