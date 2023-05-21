package com.moon.dubbo.sentinel.service;

import org.apache.dubbo.config.annotation.DubboService;

/**
 * 服务提供者接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-14 15:50
 * @description
 */
@DubboService // 标识当前类为 dubbo 的服务提供者
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }

}
