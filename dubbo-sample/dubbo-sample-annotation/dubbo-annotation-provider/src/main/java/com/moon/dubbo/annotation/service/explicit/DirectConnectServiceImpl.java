package com.moon.dubbo.annotation.service.explicit;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.DirectConnectService;

/**
 * 直连服务提供测试实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-13 20:22
 * @description
 */
@Service
public class DirectConnectServiceImpl implements DirectConnectService {

    @Override
    public String queryData() {
        System.out.println("[annotation provider] DirectConnectService 接口实现 queryData 方法执行...");
        return "[annotation provider] DirectConnectService.queryData()...";
    }

}
