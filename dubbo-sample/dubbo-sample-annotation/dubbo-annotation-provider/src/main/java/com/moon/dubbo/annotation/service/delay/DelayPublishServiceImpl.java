package com.moon.dubbo.annotation.service.delay;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.DelayPublishService;

/**
 * 延迟暴露 Dubbo 服务实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-08-01 21:54
 * @description
 */
// Dubbo 2.6.5 之前版本，如果延迟到 Spring 初始化完成后，再暴露服务，需要设置delay属性值为-1
// @Service(delay = -1)
// Dubbo 2.6.5 及以后版本，所有服务都将在 Spring 初始化完成后进行暴露，如果不需要延迟暴露服务，则无需配置 delay。
@Service(delay = 5000) // 延迟 5 秒暴露服务
public class DelayPublishServiceImpl implements DelayPublishService {

    @Override
    public String publishMessage() {
        System.out.println("[annotation provider] DelayPublishService 接口实现 publishMessage 方法执行...");
        return "annotation provider DelayPublishService response data";
    }

}
