package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.DirectConnectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 直连服务提供测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-13 20:24
 * @description
 */
@RestController
@RequestMapping("directConnect")
public class DirectConnectController {

    /*
     * @Reference 注解相当于 <dubbo:reference>
     * 配置 url 指向提供者，将绕过注册中心，多个地址用分号隔开
     */
    @Reference(url = "dubbo://127.0.0.1:20880", check = false)
    private DirectConnectService directConnectService;

    @GetMapping
    public String testDirectConnect() {
        return directConnectService.queryData();
    }

}
