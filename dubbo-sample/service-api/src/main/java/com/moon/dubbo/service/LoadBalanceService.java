package com.moon.dubbo.service;

/**
 * 负载均衡业务测试接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-11 19:28
 * @description
 */
public interface LoadBalanceService {

    /**
     * 负载均衡策略：随机，按权重设置随机概率
     *
     * @return
     */
    String random();

    /**
     * 负载均衡策略：轮询，按公约后的权重设置轮询比率
     *
     * @return
     */
    String roundRobin();

    /**
     * 负载均衡策略：最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差
     *
     * @return
     */
    String leastActive();

    /**
     * 负载均衡策略：一致性 Hash，相同参数的请求总是发到同一提供者
     *
     * @return
     */
    String consistentHash();

}
