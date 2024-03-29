package com.moon.dubbo.annotation.config;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 服务提供者配置类 - 用于配置提供者需要几个主要注解（加载配置文件式配置）
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 16:12
 * @description
 */
// @Configuration
// 开启dubbo注解扫描，指定Spring扫描包路径
@EnableDubbo(scanBasePackages = "com.moon.dubbo.annotation.service")
// 自动装配dubbo公共信息
@PropertySource("classpath:/dubbo-provider.properties")
public class ProviderPropertyConfiguration {

}
