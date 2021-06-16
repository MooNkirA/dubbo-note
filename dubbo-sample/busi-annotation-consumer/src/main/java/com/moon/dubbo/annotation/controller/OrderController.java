package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.entity.OrderEntiry;
import com.moon.service.OrderService;
import org.springframework.stereotype.Controller;

/**
 * 订单控制层，用于测试dubbo引用提供者暴露的服务接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 19:21
 * @description
 */
@Controller("orderController")
public class OrderController {

    /**
     * 使用@Reference注解自动注入dubbo框架提供方暴露的相应接口
     * 相当于xml配置文件中的<dubbo:reference />标签
     */
    @Reference
    private OrderService orderService;

    public OrderEntiry getDetail(String id) {
        return orderService.getDetail(id);
    }

}
