package com.moon.dubbo.annotation.service;

import com.moon.dubbo.service.LocalMockService;

/**
 * 消费者，本地伪装降级类。
 * <p>
 * 这个是配置 mock="true"时默认加载的降级类，其名称必须是 “接口名 + Mock”。
 * 这个类中方法调用的前提是出现了远程调用异常（RpcException），此时才会调用此类其中的方法
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-28 22:39
 * @description
 */
public class LocalMockServiceMock implements LocalMockService {

    @Override
    public String mock(String params) {
        System.out.println("[annotation consumer] LocalMockService 接口实现 mock 方法执行...");
        // 返回伪造容错数据，此方法只在远程调用出现 RpcException 时被执行
        return "[annotation consumer] LocalMockServiceMock: " + params;
    }

}
