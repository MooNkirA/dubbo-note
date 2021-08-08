package com.moon.dubbo.annotation.service.localstub;

import com.alibaba.dubbo.config.annotation.Service;
import com.moon.dubbo.service.LocalStubService;

/**
 * 本地存根实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-27 22:50
 * @description
 */
@Service
public class LocalStubServiceImpl implements LocalStubService {

    @Override
    public String execute(String params) {
        System.out.println("[annotation provider] LocalStubService 接口实现 execute 方法执行...");
        return "Annotation provider LocalStubServiceImpl execute: " + params;
    }

}
