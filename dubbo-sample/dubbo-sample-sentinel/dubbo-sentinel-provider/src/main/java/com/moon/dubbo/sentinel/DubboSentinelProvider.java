package com.moon.dubbo.sentinel;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallbackRegistry;
import com.moon.dubbo.sentinel.service.MyDubboFallback;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;

/**
 * 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-18 22:24
 * @description
 */
@EnableDiscoveryClient // 开启服务发现功能
@SpringBootApplication
public class DubboSentinelProvider {

    public static void main(String[] args) {
        SpringApplication.run(DubboSentinelProvider.class, args);
    }

    // 注册自定义的异常处理类
    @PostConstruct
    public void signUpCustomFallback() {
        DubboFallbackRegistry.setProviderFallback(new MyDubboFallback());
    }

}
