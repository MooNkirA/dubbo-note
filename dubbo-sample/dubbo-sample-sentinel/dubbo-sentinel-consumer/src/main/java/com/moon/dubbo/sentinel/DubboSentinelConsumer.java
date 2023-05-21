package com.moon.dubbo.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 启动类
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-18 22:28
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DubboSentinelConsumer {

    public static void main(String[] args) {
        SpringApplication.run(DubboSentinelConsumer.class, args);
    }

}
