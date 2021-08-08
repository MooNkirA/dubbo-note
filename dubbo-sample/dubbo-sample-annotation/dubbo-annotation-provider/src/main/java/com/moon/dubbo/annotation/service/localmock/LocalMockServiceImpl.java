package com.moon.dubbo.annotation.service.localmock;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.LocalMockService;

/**
 * 本地伪装实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-28 22:10
 * @description
 */
@Service
public class LocalMockServiceImpl implements LocalMockService {

    @Override
    public String mock(String params) {
        System.out.println("[annotation provider] LocalMockService 接口实现 mock 方法执行...");
        try {
            // 模拟调用超时
            Thread.sleep(1000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "[annotation provider] LocalMockServiceImpl: " + params;
    }

}
