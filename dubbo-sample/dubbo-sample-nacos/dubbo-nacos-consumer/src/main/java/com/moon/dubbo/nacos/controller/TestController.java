package com.moon.dubbo.nacos.controller;

import com.moon.dubbo.nacos.service.HelloService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 dubbo RPC 调用接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-14 16:08
 * @description
 */
@RestController
public class TestController {

    // @Reference(check=false) // @Reference 注解用于引用服务接口（已过时）
    // 使用 dubbo 的 @DubboReference 注解引用服务接口，其中 check 属性为 false时，启动时不会去检查是否有可用的服务接口引用
    @DubboReference(check = false)
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        // 调用服务接口
        return helloService.hello(name);
    }

}
