package com.moon.dubbo.api;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.moon.dubbo.api.service.OrderServiceImpl;
import com.moon.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * dubbo服务提供者 - 基于API配置方式
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-27 12:00
 * @description
 */
@SpringBootApplication
public class ApiProvider {

    public static void main(String[] args) {
        SpringApplication.run(ApiProvider.class, args);

        // 服务提供者暴露服务配置
        ServiceConfig<OrderService> config = new ServiceConfig<>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
        config.setApplication(new ApplicationConfig("busi-api-provider"));
        // 设置注册中心，多个注册中心可以用setRegistries()
        config.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        config.setInterface(OrderService.class);
        config.setRef(new OrderServiceImpl());

        // 暴露及注册服务
        config.export();

        System.out.println("busi-api-provider is running...");
    }

}
