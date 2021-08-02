package com.moon.dubbo.annotation.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 服务提供者配置类 - 用于配置提供者需要几个主要注解（编程式配置）
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-1-26 16:12
 * @description
 */
@Configuration
// 开启dubbo注解扫描，指定Spring扫描包路径
@EnableDubbo(scanBasePackages = "com.moon.dubbo.annotation.service")
public class ProviderConfiguration {

    /**
     * 提供者全局配置，用于减少重复的配置
     * 相当于xml配置文件中的<dubbo:provider />标签
     *
     * @return ProviderConfig
     */
    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(1000);
        return providerConfig;
    }

    /**
     * 必需配置。服务提供方应用名称
     * 相当于xml配置文件中的<dubbo:application />标签
     *
     * @return ApplicationConfig
     */
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-annotation-provider");
        return applicationConfig;
    }

    /**
     * 必需配置。注册中心配置
     * 相当于xml配置文件中的<dubbo:registry />标签
     *
     * @return RegistryConfig
     */
    @Bean("registry1")
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        // registryConfig.setId("MoonRegistry"); // 注册中心的ID，用于引用
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setPort(2181);
        // registryConfig.setCheck(true); // 属性check: 关闭所有服务的启动时检查 (没有提供者时报错)。默认值true(开启)
        // 属性register：只订阅不注册
        // registryConfig.setRegister(false);
        return registryConfig;
    }

    /**
     * 多个注册中心配置示例
     * 相当于xml配置文件中的<dubbo:registry />标签
     *
     * @return RegistryConfig
     */
    /*@Bean("ZeroRegistry")
    public RegistryConfig registryConfig2() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setId("ZeroRegistry");
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1");
        registryConfig.setPort(2181);
        return registryConfig;
    }*/

    /**
     * 必需配置。通信协议与监听端口
     * 相当于xml配置文件中的<dubbo:protocol />标签
     *
     * @return ProtocolConfig
     */
    @Bean("dubbo")
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        // name属性：指定使用协议名称。（dubbo/rmi/rest）
        protocolConfig.setName("dubbo");
        // port属性：dubbo协议缺省端口为20880，rmi协议缺省端口为1099，http和hessian协议缺省端口为80；
        // 如果没有配置port，则自动采用默认端口，如果配置为-1，则会分配一个没有被占用的端口。
        protocolConfig.setPort(20880);
        // dispatcher属性：协议的消息派发方式，用于指定线程模型，
        // 比如：dubbo协议的 all/direct/message/execution/connection 等
        protocolConfig.setDispatcher("all");
        // threadpool属性：线程池类型，可选：fixed/cached/limited/eager
        protocolConfig.setThreadpool("fixed");
        // threads属性：服务线程池大小(固定大小)，默认值200
        protocolConfig.setThreads(100);
        return protocolConfig;
    }

    /**
     * 配置多协议，rmi协议
     * 相当于xml配置文件中的<dubbo:protocol />标签
     *
     * @return ProtocolConfig
     */
    /*@Bean("rmi")
    public ProtocolConfig protocolConfigRmi() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        // name属性：指定使用协议名称。（dubbo/rmi/rest）
        protocolConfig.setName("rmi");
        // port属性：dubbo协议缺省端口为20880，rmi协议缺省端口为1099，http和hessian协议缺省端口为80；
        // 如果没有配置port，则自动采用默认端口，如果配置为-1，则会分配一个没有被占用的端口。
        protocolConfig.setPort(1099);
        return protocolConfig;
    }*/

}
