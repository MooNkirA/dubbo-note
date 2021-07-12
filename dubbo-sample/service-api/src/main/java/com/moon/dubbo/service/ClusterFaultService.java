package com.moon.dubbo.service;

import com.moon.dubbo.response.CommonResult;

/**
 * 集群容错业务接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-11 11:45
 * @description
 */
public interface ClusterFaultService {

    CommonResult doFail(String params);

}
