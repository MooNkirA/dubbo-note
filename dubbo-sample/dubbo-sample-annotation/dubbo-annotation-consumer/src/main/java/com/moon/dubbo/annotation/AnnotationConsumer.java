package com.moon.dubbo.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        SpringApplication.run(AnnotationConsumer.class, args);
        System.out.println("======== dubbo-annotation-consumer 服务启动成功 ========");
    }
}
