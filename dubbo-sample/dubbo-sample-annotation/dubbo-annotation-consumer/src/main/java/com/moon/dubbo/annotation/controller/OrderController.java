package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.entity.OrderEntiry;
import com.moon.dubbo.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单控制层，用于测试dubbo引用提供者暴露的服务接口
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 19:21
 * @description
 */
@RestController("orderController")
@RequestMapping("order")
public class OrderController {

    /**
     * 使用@Reference注解自动注入dubbo框架提供方暴露的相应接口
     * 相当于xml配置文件中的<dubbo:reference />标签
     */
    @Reference
    private OrderService orderService;

    @GetMapping("getDetail")
    public OrderEntiry getDetail(String id) {
        OrderEntiry result = orderService.getDetail(id);
        System.out.println("基于注解配置的消费者调用OrderService.getDetail()接口成功，result:  " + result.getMoney());
        return result;
    }

}
