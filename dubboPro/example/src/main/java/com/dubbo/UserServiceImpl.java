package com.dubbo;

import com.dubbo.client.User;
import com.dubbo.client.UserService;

import java.lang.management.ManagementFactory;

public class UserServiceImpl implements UserService {
    public User getUser(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("duanyi:"+ ManagementFactory.getRuntimeMXBean().getName());
        user.setSex("man");
        return user;
    }
}
