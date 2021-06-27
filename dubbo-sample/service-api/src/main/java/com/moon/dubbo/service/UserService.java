package com.moon.dubbo.service;

import com.moon.dubbo.entity.UserEntiry;

/**
 * 用户接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-22 16:41
 * @description
 */
public interface UserService {

    UserEntiry getDetail(String id);

    UserEntiry regist(UserEntiry user);

    UserEntiry recharge(String id, long money);

}
