package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.response.CommonResult;
import com.moon.dubbo.service.ClusterFaultService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 集群容错测试控制层
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-11 16:50
 * @description
 */
@RestController
@RequestMapping("clusterFault")
public class ClusterFaultController {

    /*
     * 消费者设置
     * 属性cluster：设置集群容错的模式，默认值是failover，失败重试
     * 属性retries：设置重试次数，默认值为2。如果服务提供方与消费方同时设置，则以消费方设置次数为准
     */
    @Reference(cluster = "failover", retries = 3) // 失败自动切换重试，重试retries次数
    // @Reference(cluster = "failfast") // 快速失败，只发起一次调用，失败立即报错。
    // @Reference(cluster = "failsafe") // 失败安全，出现异常时，直接忽略。
    // @Reference(cluster = "failback") // 失败自动恢复，后台记录失败请求，定时重发。
    // 并行调用多个服务器，只要一个成功即返回。通常用于实时性要求较高的读操作，但需要浪费更多服务资源。可通过 forks="2" 来设置最大并行数。
    // @Reference(cluster = "forking",  parameters = {"forks", "2"})
    // 广播调用所有提供者，逐个调用，任意一台报错则报错。通常用于通知所有提供者更新缓存或日志等本地资源信息。
    // broadcast.fail.percent=20 代表了当 20% 的节点调用失败就抛出异常，不再调用其他节点。
    // @Reference(cluster = "broadcast", parameters = {"broadcast.fail.percent", "20"})
    private ClusterFaultService clusterFaultService;

    @GetMapping
    public CommonResult testCluster() {
        return clusterFaultService.doFail("Failover Cluster");
    }

}
