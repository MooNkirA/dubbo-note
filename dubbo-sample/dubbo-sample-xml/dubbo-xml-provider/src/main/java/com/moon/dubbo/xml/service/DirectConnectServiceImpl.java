package com.moon.dubbo.xml.service;

import com.moon.dubbo.service.DirectConnectService;

/**
 * 直连服务提供测试实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-13 20:15
 * @description
 */
public class DirectConnectServiceImpl implements DirectConnectService {

    @Override
    public String queryData() {
        System.out.println("[xml provider] DirectConnectService接口实现queryDataList方法执行...");
        return "[xml provider] DirectConnectService.queryDataList()...";
    }

}
