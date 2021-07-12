package com.moon.dubbo.annotation.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * 服务消费者配置类 - 用于配置消费者需要几个主要注解
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 18:47
 * @description
 */
// @SpringBootConfiguration
// 消费方开启dubbo注解扫描，指定需要扫描的包路径。扫描如：@Reference等注解
// @EnableDubbo(scanBasePackages = {"com.moon.dubbo.annotation.controller"})
// 开启spring框架注解扫描功能。用于测试dubbo服务注入
// @ComponentScan({"com.moon.dubbo.annotation.controller"})
// 自动装配dubbo公共信息
@PropertySource("classpath:/dubbo-consumer.properties")
public class ConsumerPropertyConfiguration {

}
