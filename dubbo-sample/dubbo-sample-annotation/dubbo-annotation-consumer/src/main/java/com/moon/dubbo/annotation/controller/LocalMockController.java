package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.moon.dubbo.service.LocalMockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 本地伪装测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-28 22:51
 * @description
 */
@RestController
@RequestMapping("localMock")
public class LocalMockController {

    /*
     * mock属性：用于配置本地伪装
     *  mock = "true"，默认加载的降级类，其名称必须是 “接口名 + Mock”，并且与接口在同一个包下
     *  mock = "类全限定名"，指定降级类，此类可以在任意包下，名称也没有固定限制
     *  mock = "return xxx"，返回一个字符串表示的对象，作为 Mock 的返回值。
     *  mock = "throw xxxException"，当调用出错时，抛出指定的 Exception：
     *  mock = "force:throw xxxException" 或 mock = "force:return xxx"
     *      force: 代表强制使用 Mock 行为，在这种情况下不会走远程调用。(默认是fail，是远程调用发生错误时才使用 Mock 行为)
     */
    // @Reference(check = false, mock = "true")
    // @Reference(check = false, mock = "com.moon.dubbo.service.LocalMockServiceMock")
    // @Reference(check = false, mock = "return MooN")
    // @Reference(check = false, mock = "throw com.moon.dubbo.annotation.exception.CustomException")

    @Reference(check = false, mock = "force:return MooN")
    private LocalMockService localMockService;

    @GetMapping
    public String testLocalMock(@RequestParam("params") String params) {
        return localMockService.mock(params);
    }

}
