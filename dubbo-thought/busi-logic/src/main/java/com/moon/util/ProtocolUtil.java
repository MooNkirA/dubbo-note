package com.moon.util;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.remoting.http.HttpBinder;
import com.alibaba.dubbo.remoting.http.jetty.JettyHttpBinder;
import com.alibaba.dubbo.rpc.Protocol;
import com.alibaba.dubbo.rpc.ProxyFactory;
import com.alibaba.dubbo.rpc.protocol.dubbo.DubboProtocol;
import com.alibaba.dubbo.rpc.protocol.http.HttpProtocol;
import com.alibaba.dubbo.rpc.protocol.rmi.RmiProtocol;
import com.moon.service.DemoService;

/**
 * Dubbo协议工具类
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-7 12:18
 * @description
 */
public class ProtocolUtil {

    // http协议
    public static URL HTTP_URL = URL.valueOf("http://127.0.0.1:9010/" + DemoService.class.getName());
    // rmi协议
    public static URL RMI_URL = URL.valueOf("rmi://127.0.0.1:9001/" + DemoService.class.getName());
    // dubbo协议
    public static URL DUBBO_URL = URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName());

    public static Protocol getHttpProtocol(ProxyFactory proxy) {
        HttpBinder binder = new JettyHttpBinder();
        HttpProtocol protocol = new HttpProtocol();

        protocol.setHttpBinder(binder);
        protocol.setProxyFactory(proxy);

        return protocol;
    }

    public static Protocol getRmiProtocol(ProxyFactory proxy) {
        RmiProtocol protocol = new RmiProtocol();
        protocol.setProxyFactory(proxy);

        return protocol;
    }

    public static Protocol getDubboProtocol(ProxyFactory... proxy) {
        return new DubboProtocol();
    }
}
