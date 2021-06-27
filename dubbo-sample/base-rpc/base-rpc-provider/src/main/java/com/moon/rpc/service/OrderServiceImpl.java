package com.moon.rpc.service;

import com.moon.dubbo.entity.OrderEntiry;
import com.moon.dubbo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 订单接口实现类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-22 16:49
 * @description
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public OrderEntiry getDetail(String id) {
        LOGGER.info("{}被调用一次：{}", super.getClass().getName(), System.currentTimeMillis());

        OrderEntiry orderEntiry = new OrderEntiry();
        orderEntiry.setId("O0001");
        orderEntiry.setMoney(1000);
        orderEntiry.setUserId("U0001");

        return orderEntiry;
    }
}
