package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.LocalCallService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本地调用测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-25 17:15
 * @description
 */
@RestController
@RequestMapping("localCall")
public class LocalCallController {

    /*
     * injvm属性：配置使用 injvm 协议。不发起远程调用，只在 JVM 内直接关联。
     * 默认值是false，使用远程调用
     */
    @Reference(check = false, injvm = true)
    private LocalCallService localCallService;

    @GetMapping
    public String testLocalCall() {
        return localCallService.getCache();
    }

}
