package com.moon.dubbo.annotation;

import com.moon.dubbo.annotation.controller.OrderController;
import com.moon.dubbo.entity.OrderEntiry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * dubbo服务消费者 - 基于注解方式
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 18:42
 * @description
 */
@SpringBootApplication
public class AnnotationConsumer {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AnnotationConsumer.class, args);
        System.out.println("======== dubbo-annotation-consumer 服务启动成功 ========");

        // 获取控制层的实例
        OrderController orderController = context.getBean(OrderController.class);
        OrderEntiry entiry = orderController.getDetail("1");
        System.out.println("基于注解配置的消费者调用OrderService.getDetail()接口成功，result:  " + entiry.getMoney());
    }
}
