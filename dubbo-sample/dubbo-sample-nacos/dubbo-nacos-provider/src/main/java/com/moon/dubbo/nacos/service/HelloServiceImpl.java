package com.moon.dubbo.nacos.service;

import org.apache.dubbo.config.annotation.DubboService;

/**
 * 服务提供者接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-14 15:50
 * @description
 */
// @Service // org.apache.dubbo.config.annotation.Service 注解已过时
@DubboService // 标识当前类为 dubbo 的服务提供者
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }

}
