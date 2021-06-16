package com.moon;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
import com.moon.protocol.RmiProtocol;
import com.moon.protocol.SimpleInvoker;
import com.moon.service.DemoService;
import com.moon.service.impl.spring.DemoServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * RPC 简单协议调用示例 - 使用Rmi网络协议
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-5 17:52
 * @description
 */
public class RpcProtocolTest {

    URL url = URL.valueOf("rmi://127.0.0.1:9001/" + DemoService.class.getName());

    /**
     * Protocol连接服务端invoker
     * 将目标服务调用信息，包装成为invoker实体，暴露到网络上
     * <p>
     * 当网络信息到达，将触发invoker的invoke方法，最终将调用转到目标service上
     */
    @Test
    public void invoker2protocol() throws IOException {
        DemoService service = new DemoServiceImpl();

        // 这里将目标对象（即DemoService实例）引入到Invoker中
        Invoker<DemoService> invoker = new SimpleInvoker(service, DemoService.class, url);
        Protocol protocol = new RmiProtocol();
        // 暴露对象。协议对象只需要调用即可
        protocol.export(invoker);
        System.out.println("Dubbo server 启动");
        // 保证服务一直开着
        System.in.read();
    }

    /**
     * Protocol连接消费端invoker
     * 将要调用的信息，包装成invoker实体，向网络发送
     * <p>
     * 本地调用接口代理时，最终方法被转到invoker的invoke方法上，向网络发送
     */
    @Test
    public void protocol2Invoker() {
        // 创建协议
        Protocol protocol = new RmiProtocol();

        // 创建消费端invoker，负责发送协议调用信息
        Invoker<DemoService> invoker = protocol.refer(DemoService.class, url);

        // 做一个动态代理，将调用目标指向invoker即可
        DemoService service = (DemoService) Proxy
                .newProxyInstance(Thread.currentThread().getContextClassLoader(),
                        new Class[]{invoker.getInterface()},
                        new InvokerInvocationHandler(invoker)); // 反射逻辑

        String result = service.sayHello("moon");
        System.out.println(result);
    }

}
