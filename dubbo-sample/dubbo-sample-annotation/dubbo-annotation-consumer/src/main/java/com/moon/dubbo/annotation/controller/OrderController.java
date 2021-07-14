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

    /*
     * 使用@Reference注解自动注入dubbo框架提供方暴露的相应接口
     * 相当于xml配置文件中的<dubbo:reference />标签
     *  属性check: 关闭某个服务的启动时检查 (没有提供者时报错)
     *  属性retries: 设置重试次数，默认值是2
     *  属性timeout: 设置超时时间，默认值是0
     */
    @Reference(check = false, retries = 3, timeout = 5000)
    private OrderService orderService;

    @GetMapping("getDetail")
    public OrderEntiry getDetail(String id) {
        OrderEntiry result = orderService.getDetail(id);
        System.out.println("基于注解配置的消费者调用OrderService.getDetail()接口成功，result:  " + result.getMoney());
        return result;
    }

}
