package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.MultiProtocolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 多协议测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-15 16:42
 * @description
 */
@RestController
@RequestMapping("multiProtocol")
public class MultiProtocolController {

    @Reference
    private MultiProtocolService multiProtocolService;

    @GetMapping
    public List<String> testMultiProtocol() {
        return multiProtocolService.queryDataByProtocol();
    }

}
