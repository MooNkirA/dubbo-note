package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.DelayPublishService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 延迟暴露 Dubbo 服务测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-08-01 21:57
 * @description
 */
@RestController
@RequestMapping("delayPublish")
public class DelayPublishController {

    @Reference(check = false)
    private DelayPublishService delayPublishService;

    @GetMapping
    public String testDelayPublish() {
        return delayPublishService.publishMessage();
    }

}
