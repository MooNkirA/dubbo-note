package com.moon.protocol;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.Version;
import com.alibaba.dubbo.rpc.Exporter;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.protocol.rmi.RmiRemoteInvocation;
import com.alibaba.dubbo.rpc.proxy.InvokerInvocationHandler;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.lang.reflect.Proxy;
import java.rmi.RemoteException;

/**
 * 自定义 Rmi 协议，实现dubbo框架的 Protocol 接口，用于理解接口功能
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-5 13:26
 * @description
 */
public class RmiProtocol implements Protocol {

    private static final int DEFAULT_PORT = 1099;

    @Override
    public int getDefaultPort() {
        return DEFAULT_PORT;
    }

    /* 以rmi协议为示例，暴露服务 */
    @Override
    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        // 创建spring rmi服务
        final RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();

        // 设置
        rmiServiceExporter.setRegistryPort(invoker.getUrl().getPort());
        rmiServiceExporter.setServiceName(invoker.getUrl().getPath());
        rmiServiceExporter.setServiceInterface(invoker.getInterface());

        /*
         * 此时目标服务没有，需要通过invoker调通，使用动态代理
         *   这里希望通过invoker对象，制作一个target服务代理来使用
         */
        T service = (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{invoker.getInterface()},
                new InvokerInvocationHandler(invoker));

        /*
         * 如果能获取目标Service接口，直接设置即可
         *   protocol协议的目标，都是要将调用转到目标target服务上，但是当前环境并没有这个目标target
         */
        rmiServiceExporter.setService(service);
        try {
            rmiServiceExporter.afterPropertiesSet();
        } catch (RemoteException e) {
            throw new RpcException(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        return new SimpleInvoker(doRefer(type, url), type, url);
    }

    private <T> T doRefer(Class<T> type, URL url) throws RpcException {
        final RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        if (url.getParameter(Constants.DUBBO_VERSION_KEY, Version.getProtocolVersion()).equals(Version.getProtocolVersion())) {
            /*rmiProxyFactoryBean.setRemoteInvocationFactory(new RemoteInvocationFactory() {
                @Override
                public RemoteInvocation createRemoteInvocation(MethodInvocation methodInvocation) {
                    return new RmiRemoteInvocation(methodInvocation);
                }
            });*/
            rmiProxyFactoryBean.setRemoteInvocationFactory(RmiRemoteInvocation::new);
        }
        rmiProxyFactoryBean.setServiceUrl(url.toIdentityString());
        rmiProxyFactoryBean.setServiceInterface(type);
        rmiProxyFactoryBean.setCacheStub(true);
        rmiProxyFactoryBean.setLookupStubOnStartup(true);
        rmiProxyFactoryBean.setRefreshStubOnConnectFailure(true);
        rmiProxyFactoryBean.afterPropertiesSet();
        return (T) rmiProxyFactoryBean.getObject();
    }

    @Override
    public void destroy() {

    }
}
