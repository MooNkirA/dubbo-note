package com.moon.dubbo.annotation.service;

import com.alibaba.dubbo.config.annotation.Method;
import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.LoadBalanceService;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 负载均衡业务实现测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-11 19:34
 * @description
 */
// 负载均衡策略的配置：服务端服务级别
// @Service(loadbalance = "roundrobin")
// 负载均衡策略的配置：服务端方法级别
@Service(methods = {
        @Method(name = "random", loadbalance = "random"),
        @Method(name = "roundRobin", loadbalance = "roundrobin"),
        @Method(name = "leastActive", loadbalance = "leastactive"),
        @Method(name = "consistentHash", loadbalance = "consistenthash")
})
public class LoadBalanceServiceImpl implements LoadBalanceService, EnvironmentAware {

    private final static String interfaceName;
    private static String ip;
    private String port;

    static {
        interfaceName = LoadBalanceServiceImpl.class.getInterfaces()[0].getSimpleName();
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 负载均衡策略：随机，按权重设置随机概率
     *
     * @return
     */
    @Override
    public String random() {
        System.out.println("[annotation provider " + ip + ":" + port + "] " + interfaceName + "接口random()方法执行 +++++");
        return "Random LoadBalance";
    }

    /**
     * 负载均衡策略：轮询，按公约后的权重设置轮询比率
     *
     * @return
     */
    @Override
    public String roundRobin() {
        System.out.println("[annotation provider " + ip + ":" + port + "] " + interfaceName + "接口roundRobin()方法执行 +++++");
        return "RoundRobin LoadBalance";
    }

    /**
     * 负载均衡策略：最少活跃调用数，相同活跃数的随机，活跃数指调用前后计数差
     *
     * @return
     */
    @Override
    public String leastActive() {
        System.out.println("[annotation provider " + ip + ":" + port + "] " + interfaceName + "接口leastActive()方法执行 +++++");
        return "LeastActive LoadBalance";
    }

    /**
     * 负载均衡策略：一致性 Hash，相同参数的请求总是发到同一提供者
     *
     * @return
     */
    @Override
    public String consistentHash() {
        System.out.println("[annotation provider " + ip + ":" + port + "] " + interfaceName + "接口consistentHash()方法执行 +++++");
        return "ConsistentHash LoadBalance";
    }

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        port = environment.getProperty("server.port");
    }
}
