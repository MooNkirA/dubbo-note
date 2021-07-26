package com.moon.dubbo.annotation.service.local;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.LocalCallService;

/**
 * 服务提供者实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-25 17:11
 * @description
 */
// @Service
public class LocalCallServiceImpl implements LocalCallService {

    @Override
    public String getCache() {
        System.out.println("[annotation provider] LocalCallService 接口实现 getCache 方法执行...");
        return "provider cache data";
    }

}
