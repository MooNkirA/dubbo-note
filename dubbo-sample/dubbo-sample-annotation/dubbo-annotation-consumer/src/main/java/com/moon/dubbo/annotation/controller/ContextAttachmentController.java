package com.moon.dubbo.annotation.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.moon.dubbo.service.ContextAttachmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 上下文信息-隐式参数传递测试
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-21 22:34
 * @description
 */
@RestController
@RequestMapping("context")
public class ContextAttachmentController {

    @Reference
    private ContextAttachmentService contextAttachmentService;

    @GetMapping("attachment")
    public Map<String, Object> testCluster() {
        // 隐式传参，后面的远程调用都会隐式将这些参数发送到服务器端，类似cookie，用于框架集成，不建议常规业务使用
        RpcContext context = RpcContext.getContext();
        context.setAttachment("index", "1");
        context.setAttachment("name", "MooN");
        context.setAttachment("num", "18");

        return contextAttachmentService.getContextData();
    }

}
