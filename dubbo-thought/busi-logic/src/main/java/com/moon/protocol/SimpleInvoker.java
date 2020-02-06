package com.moon.protocol;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.moon.service.DemoService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 一个简单的dubbo框架Invoker接口实现，用于了解Invoker接口的作用
 *
 * @author MoonZero
 * @version 1.0
 * @date 2020-2-5 12:04
 * @description
 */
public class SimpleInvoker<T> implements Invoker<T> {

    private T target;
    private Class<T> type;
    private URL url;

    public SimpleInvoker(T service, Class<T> type, URL url) {
        this.target = service;
        this.type = type;
        this.url = url;
    }

    @Override
    public Class<T> getInterface() {
        return type;
    }

    @Override
    public Result invoke(Invocation invocation) throws RpcException {
        Method method = null;
        try {
            method = DemoService.class.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            return new RpcResult(method.invoke(target, invocation.getArguments()));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public URL getUrl() {
        return url;
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void destroy() {

    }
}
