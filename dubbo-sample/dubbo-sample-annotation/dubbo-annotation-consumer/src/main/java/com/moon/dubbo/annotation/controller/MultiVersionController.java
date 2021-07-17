package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.MultiVersionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多版本测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 11:46
 * @description
 */
@RestController
@RequestMapping("multiVersion")
public class MultiVersionController {

    /*
     * version属性：配置引用的服务接口的版本号
     *   如果不需要区分版本，可以配置"*"
     */
    @Reference(version = "1.0.0")
    // @Reference(version = "*") // 不区分版本号
    private MultiVersionService multiVersionService;

    @GetMapping
    public String testMultiVersion() {
        return multiVersionService.getVersion();
    }

}
