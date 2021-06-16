package com.moon;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.moon.protocol.SimpleInvoker;
import com.moon.service.DemoService;
import com.moon.service.impl.spring.DemoServiceImpl;
import org.junit.Test;

import java.io.IOException;

/**
 * TODO:
 * RPC 简单协议调用示例 - 使用Dubbo框架SPI扩展实现（！！！！！有问题，待排查）
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-7 14:05
 * @description
 */
public class RpcSPITest {

    URL url = URL.valueOf("rmi://127.0.0.1:9001/" + DemoService.class.getName());

    ExtensionLoader<Protocol> protocolLoader = ExtensionLoader.getExtensionLoader(Protocol.class);
    ExtensionLoader<ProxyFactory> proxyLoader = ExtensionLoader.getExtensionLoader(ProxyFactory.class);

    /**
     * Protocol连接服务端invoker
     */
    @Test
    public void protocol2Invoker() throws IOException {
        DemoService service = new DemoServiceImpl();

        Invoker<DemoService> invoker = new SimpleInvoker(service, DemoService.class, url);
        Protocol protocol = protocolLoader.getExtension("rmi");
        // 暴露对象
        protocol.export(invoker);
        System.out.println("Dubbo server 启动");
        // 保证服务一直开着
        System.in.read();
    }

    /**
     * Protocol连接消费端invoker
     */
    @Test
    public void rpcClient() {
        // 动态代理
        ProxyFactory proxy = proxyLoader.getExtension("jdk");
        // 获取自适应的协议
        Protocol protocol = protocolLoader.getAdaptiveExtension();

        // 消费invoker，负责发送协议调用信息
        Invoker<DemoService> invoker = protocol.refer(DemoService.class, url);

        // 做一个动态代理，将调用目标指向invoker即可
        DemoService service = proxy.getProxy(invoker);

        String result = service.sayHello("Moon");
        System.out.println(result);
    }

    /***********************服务切换********************************/

    // 支持的协议：dubbo,http,hessian,rmi
    URL protocol_url = URL.valueOf("http://127.0.0.1:9020/" + DemoService.class.getName());

    @Test
    public void serverRpc() throws IOException {
        DemoService service = new DemoServiceImpl();
        Protocol protocol = protocolLoader.getAdaptiveExtension();
        // 动态代理
        ProxyFactory proxy = proxyLoader.getAdaptiveExtension();
        // 暴露服务
        Invoker<DemoService> serviceInvoker = proxy.getInvoker(service, DemoService.class, protocol_url);
        Exporter<DemoService> exporter = protocol.export(serviceInvoker);
        System.out.println("server 启动协议：" + protocol_url.getProtocol());
        // 保证服务一直开着
        System.in.read();
        exporter.unexport();
    }

    @Test
    public void clientRpc() {
        Protocol protocol = protocolLoader.getAdaptiveExtension();
        //动态代理
        ProxyFactory proxy = proxyLoader.getAdaptiveExtension();
        //消费服务
        Invoker<DemoService> referInvoker = protocol.refer(DemoService.class, protocol_url);
        DemoService service = proxy.getProxy(referInvoker);

        String result = service.sayHello(protocol_url.getProtocol() + "调用");
        System.out.println(result);
    }

}
