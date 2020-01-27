package com.moon.dubbo.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.moon.entity.OrderEntiry;
import com.moon.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ubbo服务消费者 - 基于API配置方式
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-27 12:17
 * @description
 */
@SpringBootApplication
public class ApiConsumer {

    public static void main(String[] args) {
        SpringApplication.run(ApiConsumer.class, args);

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接
        // 引用远程服务
        ReferenceConfig<OrderService> reference = new ReferenceConfig<>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(new ApplicationConfig("busi-api-consumer"));
        // 设置注册中心，多个注册中心可以用setRegistries()
        reference.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        reference.setInterface(OrderService.class);

        System.out.println("busi-api-consumer is running...");

        // 和本地bean一样使用xxxService
        OrderService orderService = reference.get();
        OrderEntiry entiry = orderService.getDetail("2");
        System.out.println("基于API配置的消费者调用OrderService.getDetail()接口成功，result: " + entiry.getMoney());

    }

}
