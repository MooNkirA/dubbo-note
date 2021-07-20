package com.moon.dubbo.annotation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.entity.UserEntiry;
import com.moon.dubbo.service.UserService;

import java.util.UUID;

/**
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-19 22:44
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserEntiry getDetail(String id) {
        return null;
    }

    @Override
    public UserEntiry regist(UserEntiry user) {
        System.out.println("[annotation provider] UserService 接口实现 regist 方法执行...");
        user.setId(UUID.randomUUID().toString().substring(0, 10));
        return user;
    }

    @Override
    public UserEntiry recharge(String id, long money) {
        return null;
    }

}
