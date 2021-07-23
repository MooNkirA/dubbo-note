package com.moon.dubbo.annotation.service.fault;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.response.CommonResult;
import com.moon.dubbo.service.ClusterFaultService;

/**
 * 集群容错业务实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-11 16:16
 * @description
 */
// 属性retries：设置重试次数
@Service(retries = 5)
public class ClusterFaultServiceImpl implements ClusterFaultService {

    @Override
    public CommonResult doFail(String params) {
        System.out.println("[annotation provider] ClusterFaultService接口实现doFail方法执行 +++++");
        try {
            Thread.sleep(10000); // 模拟超时
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CommonResult.success(null);
    }

}
