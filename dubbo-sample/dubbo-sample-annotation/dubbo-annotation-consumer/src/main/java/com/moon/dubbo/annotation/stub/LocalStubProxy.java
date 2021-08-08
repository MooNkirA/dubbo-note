package com.moon.dubbo.annotation.stub;

import com.moon.dubbo.service.LocalStubService;

/**
 * 本地存根，代理。
 * 此类与服务提供者实现同一个接口，但会接管服务提供者的接口调用。
 * 通过此代理可以实现要不要远程调用服务提供方
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-27 22:55
 * @description
 */
public class LocalStubProxy implements LocalStubService {

    private final LocalStubService localStubService;

    // 注意，此构造函数必须定义，用于传入真正的远程代理对象
    public LocalStubProxy(LocalStubService localStubService) {
        this.localStubService = localStubService;
    }

    @Override
    public String execute(String params) {
        System.out.println("[annotation consumer] LocalStubService 接口实现 execute 方法执行...");

        // 此代码在客户端执行, 你可以在客户端做ThreadLocal本地缓存，或预先验证参数是否合法，等等
        try {
            if ("local".equalsIgnoreCase(params)) {
                // 模拟业务，不调用远程接口
                return "Annotation consumer LocalStubProxy execute: " + params;
            } else if ("remote".equalsIgnoreCase(params)) {
                // 模拟业务，调用远程服务
                return localStubService.execute(params);
            }
        } catch (Exception e) {
            // 可以容错，可以做任何AOP拦截事项
            e.printStackTrace();
            return "服务器出错了";
        }

        return null;
    }

}
