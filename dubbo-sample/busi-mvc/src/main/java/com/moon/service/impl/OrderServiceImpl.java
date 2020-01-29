package com.moon.service.impl;

import com.moon.dao.OrderDao;
import com.moon.entity.OrderEntiry;
import com.moon.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public OrderEntiry getDetail(String id) {
        System.out.println(super.getClass().getName()+"被调用一次："+System.currentTimeMillis());
        OrderEntiry orderEntiry =  orderDao.getDetail(id);
//        try {
//            Thread.sleep(2000);//休眼2s，测试超时功能
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return orderEntiry;
    }
}
