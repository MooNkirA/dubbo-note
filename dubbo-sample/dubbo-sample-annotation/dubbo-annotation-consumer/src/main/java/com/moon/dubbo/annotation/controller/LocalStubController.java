package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.LocalStubService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本地存根测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-27 23:08
 * @description
 */
@RestController
@RequestMapping("localStub")
public class LocalStubController {

    @Reference(check = false, stub = "com.moon.dubbo.annotation.stub.LocalStubProxy")
    private LocalStubService localStubService;

    @GetMapping
    public String testLocalStub(@RequestParam("params") String params) {
        return localStubService.execute(params);
    }


}
