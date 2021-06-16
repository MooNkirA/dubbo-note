package com.moon.dubbo.xml;

import com.moon.entity.OrderEntiry;
import com.moon.service.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * dubbo服务消费者 - 基于xml配置方式
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 15:27
 * @description
 */
@SpringBootApplication
// 读取dubbo消费者配置文件
@ImportResource({"classpath:spring/dubbo-consumer.xml"})
public class XmlClient {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(XmlClient.class, args);

        System.out.println("======== busi-xml-client服务启动成功 ========");
        OrderService orderService = context.getBean(OrderService.class); // get remote service proxy

        OrderEntiry entiry = orderService.getDetail("1");
        System.out.println("基于xml配置的消费者调用OrderService.getDetail()接口成功，result: " + entiry.getMoney());
    }

}
