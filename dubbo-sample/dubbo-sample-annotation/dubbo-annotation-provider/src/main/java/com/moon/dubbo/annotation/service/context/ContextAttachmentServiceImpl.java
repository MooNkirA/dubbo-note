package com.moon.dubbo.annotation.service.context;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.moon.dubbo.service.ContextAttachmentService;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下文信息-隐式参数传递测试实现
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-21 22:30
 * @description
 */
@Service
public class ContextAttachmentServiceImpl implements ContextAttachmentService {

    @Override
    public Map<String, Object> getContextData() {
        System.out.println("[annotation provider] ContextAttachmentService 接口实现 getContextData 方法执行...");
        // 获取客户端隐式传入的参数，用于框架集成，不建议常规业务使用
        RpcContext context = RpcContext.getContext();
        Map<String, Object> result = new HashMap<>();
        result.put("index", context.getAttachment("index"));
        result.put("name", context.getAttachment("name"));
        result.put("num", context.getAttachment("num"));
        return result;
    }
}
