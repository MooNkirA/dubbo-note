package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Method;
import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.ConcurrencyControlService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 并发控制测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-08-02 22:41
 * @description
 */
@RestController
@RequestMapping("concurrency-control")
public class ConcurrencyControlController {

    /* 限制 ConcurrencyControlService 类中每个方法，每客户端并发执行（或占用线程池线程数）不能超过 8 个 */
    // @Reference(check = false, actives = 8)
    /*
     * 限制 ConcurrencyControlService 类中指定的方法，每客户端并发执行（或占用线程池线程数）不能超过 8 个
     *      注：@Service 与 @Reference 同时配置了 actives 属性，则以 @Reference 优化
     */
    @Reference(check = false, methods = {@Method(name = "concurrencyExecute", actives = 8)})
    private ConcurrencyControlService concurrencyControlService;

    @GetMapping
    public String testConcurrencyControl() {
        return concurrencyControlService.concurrencyExecute();
    }

}
