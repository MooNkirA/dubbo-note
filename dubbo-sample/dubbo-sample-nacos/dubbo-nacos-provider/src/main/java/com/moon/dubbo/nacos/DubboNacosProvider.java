package com.moon.dubbo.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-14 15:56
 * @description
 */
@EnableDiscoveryClient // 开启服务发现功能
@SpringBootApplication
public class DubboNacosProvider {

    public static void main(String[] args) {
        SpringApplication.run(DubboNacosProvider.class, args);
    }

}
