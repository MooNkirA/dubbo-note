package com.moon;

import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcResult;
import com.alibaba.dubbo.rpc.protocol.AbstractInvoker;
import com.alibaba.dubbo.rpc.proxy.javassist.JavassistProxyFactory;
import com.alibaba.dubbo.rpc.proxy.jdk.JdkProxyFactory;
import com.moon.service.DemoService;
import com.moon.service.impl.spring.DemoServiceImpl;
import com.moon.util.ProtocolUtil;
import org.junit.Test;

/**
 * dubbo中的动态代理与javassist静态代理示例
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-7 12:27
 * @description
 */
public class ProxyTest {

    /**
     * 封装目标服务为统一 Invoker
     */
    private Invoker<DemoService> invoker = new AbstractInvoker(DemoService.class, ProtocolUtil.RMI_URL) {
        @Override
        protected Result doInvoke(Invocation invocation) throws Throwable {
            Object[] objects = invocation.getArguments();
            DemoService demoService = new DemoServiceImpl();
            Object o = demoService.sayHello((String) objects[0]);
            return new RpcResult(o);
        }
    };

    /**
     * jdk动态代理
     */
    @Test
    public void JdkProxyTest() {
        // 动态代理
        JdkProxyFactory proxy = new JdkProxyFactory();

        DemoService service = proxy.getProxy(invoker, new Class[]{DemoService.class});
        String result = service.sayHello("Dubbo的jdk动态代理");
        System.out.println(result);

    }

    /**
     * javassist静态代理
     */
    @Test
    public void javassistProxyTest() {
        // 静态代理
        JavassistProxyFactory proxy = new JavassistProxyFactory();

        DemoService service = proxy.getProxy(invoker, new Class[]{DemoService.class});
        String result = service.sayHello("Dubbo的javassist静态代理");
        System.out.println(result);

    }

}
