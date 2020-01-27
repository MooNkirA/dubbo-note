package com.moon.dubbo.annotation.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

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
public class ConsumerConfiguration {

    /**
     * 必需配置。服务消费方应用名称
     * 相当于xml配置文件中的<dubbo:application />标签
     *
     * @return ApplicationConfig
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("busi-annotation-consumer");
        return applicationConfig;
    }

    /**
     * 消费者全局配置，用于减少重复的配置
     * 相当于xml配置文件中的<dubbo:consumer />标签
     *
     * @return ConsumerConfig
     */
    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setTimeout(3000);
        return consumerConfig;
    }

    /**
     * 必需配置。配置注册中心信息
     * 相当于xml配置文件中的<dubbo:registry />标签
     *
     * @return RegistryConfig
     */
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setPort(2181);
        return registryConfig;
    }

}
