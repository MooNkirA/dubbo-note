package com.moon.service.impl;

import com.moon.entity.UserEntiry;
import com.moon.service.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public UserEntiry getDetail(String id) {
        try {
            System.out.println("UserService.getDetail耗时2秒");
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        UserEntiry user = new UserEntiry();
        user.setId("U001");
        user.setName("MoooooooooN");
        user.setAddress("广州");
        user.setBalance(5000);
        return user;
    }

    @Override
    public UserEntiry regist(UserEntiry user) {
        return null;
    }

    @Override
    public UserEntiry recharge(String id, long money) {
        return null;
    }


}
