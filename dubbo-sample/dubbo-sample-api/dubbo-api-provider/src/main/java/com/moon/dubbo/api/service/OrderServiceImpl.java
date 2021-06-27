package com.moon.dubbo.api.service;

import com.moon.dubbo.entity.OrderEntiry;
import com.moon.dubbo.service.OrderService;

/**
 * 订单接口实现
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 15:08
 * @description
 */
public class OrderServiceImpl implements OrderService {

    @Override
    public OrderEntiry getDetail(String id) {
        System.out.println(super.getClass().getName() + "被调用一次：" + System.currentTimeMillis());
        OrderEntiry orderEntiry = new OrderEntiry();
        orderEntiry.setId("O0001");
        orderEntiry.setMoney(5000);
        orderEntiry.setUserId("U0001");

        return orderEntiry;
    }

}
