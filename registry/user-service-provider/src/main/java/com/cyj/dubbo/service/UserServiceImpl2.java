package com.cyj.dubbo.service;

import com.cyj.dubbo.model.User;

public class UserServiceImpl2 implements UserService {

    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setId(id + 1);
        user.setName("zhangsan" + (id + 1));
        return user;
    }
}
