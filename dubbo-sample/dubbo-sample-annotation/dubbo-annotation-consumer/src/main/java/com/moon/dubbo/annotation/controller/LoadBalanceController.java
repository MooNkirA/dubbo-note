package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Method;
import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.LoadBalanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 负载均衡测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-11 19:45
 * @description
 */
@RestController
@RequestMapping("loadBalance")
public class LoadBalanceController {

    // 负载均衡策略的配置：客户端服务级别
    // @Reference(loadbalance = "random")
    // 负载均衡策略的配置：客户端方法级别
    @Reference(methods = {
            @Method(name = "random", loadbalance = "random"),
            @Method(name = "roundRobin", loadbalance = "roundrobin"),
            @Method(name = "leastActive", loadbalance = "leastactive"),
            @Method(name = "consistentHash", loadbalance = "consistenthash")
    })
    private LoadBalanceService loadBalanceService;

    @GetMapping("random")
    public String testRandom() {
        return loadBalanceService.random();
    }

    @GetMapping("roundRobin")
    public String testRoundRobin() {
        return loadBalanceService.roundRobin();
    }

    @GetMapping("leastActive")
    public String testLeastActive() {
        return loadBalanceService.leastActive();
    }

    @GetMapping("consistentHash")
    public String testConsistentHash() {
        return loadBalanceService.consistentHash();
    }

}
