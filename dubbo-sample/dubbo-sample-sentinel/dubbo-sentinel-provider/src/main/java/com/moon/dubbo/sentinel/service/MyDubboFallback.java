package com.moon.dubbo.sentinel.service;

import com.alibaba.csp.sentinel.adapter.dubbo.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

/**
 * dubbo 自定义异常处理
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2023-05-20 22:29
 * @description
 */
public class MyDubboFallback implements DubboFallback {

    @Override
    public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
        Result result = invoker.invoke(invocation);
        result.setValue("自定义异常处理");
        return result;
    }

}
