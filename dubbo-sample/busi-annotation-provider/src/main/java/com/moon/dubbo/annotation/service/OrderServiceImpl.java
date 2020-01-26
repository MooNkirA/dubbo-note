package com.moon.dubbo.annotation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.entity.OrderEntiry;
import com.moon.service.OrderService;

/**
 * 订单接口实现
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 15:08
 * @description
 */
// @Service注解相当于xml配置文件中的<dubbo:service />标签
@Service // 注意：此@Service注解不是spring框架的注解，是dobbo框架的注解
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderEntiry getDetail(String id) {
        System.out.println(super.getClass().getName() + "被调用一次：" + System.currentTimeMillis());
        OrderEntiry orderEntiry = new OrderEntiry();
        orderEntiry.setId("O0001");
        orderEntiry.setMoney(2000);
        orderEntiry.setUserId("U0001");

        return orderEntiry;
    }

}
