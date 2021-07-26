package com.moon.dubbo.annotation.service.local;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.LocalCallService;

/**
 * 消费端本地实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-25 17:13
 * @description
 */
@Service
public class LocalCallServiceImpl implements LocalCallService {

    @Override
    public String getCache() {
        System.out.println("[annotation consumer] LocalCallService 接口实现 getCache 方法执行...");
        return "consumer cache data";
    }

}
