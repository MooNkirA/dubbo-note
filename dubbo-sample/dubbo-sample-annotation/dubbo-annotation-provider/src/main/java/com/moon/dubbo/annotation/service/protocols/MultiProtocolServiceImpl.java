package com.moon.dubbo.annotation.service.protocols;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.MultiProtocolService;

import java.util.Arrays;
import java.util.List;

/**
 * 多协议测试接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-15 16:31
 * @description
 */
// protocol属性；设置同一个服务，使用多个协议。
// 注意：协议的名称是在 ProviderConfiguration 配置类中的返回 ProtocolConfig 实例方法中设置
@Service(protocol = {"dubbo", "rmi"})
public class MultiProtocolServiceImpl implements MultiProtocolService {

    @Override
    public List<String> queryDataByProtocol() {
        System.out.println("[annotation provider] MultiProtocolService接口实现queryDataByProtocol方法执行...");
        return Arrays.asList("MooN", "kirA");
    }

}
