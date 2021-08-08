package com.moon.dubbo.annotation.service.version;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.MultiVersionService;

/**
 * 多版本接口实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-17 11:40
 * @description
 */
/*
 * version属性：指定服务的版本号，建议使用两位数字版本，
 *  如：1.0，通常在接口不兼容时版本号才需要升级
 */
@Service(version = "1.0.0")
public class MultiVersionServiceImpl1 implements MultiVersionService {

    @Override
    public String getVersion() {
        System.out.println("[annotation provider] MultiVersionService接口[1.0.0]版本实现getVersion方法执行...");
        return "this provider version is 1.0.0";
    }

}
