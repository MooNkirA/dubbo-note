package com.moon.service.impl.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * 使用方传递了group = moon 则该Filter激活
 */
@Activate(group = "moon", order = 3)
public class FilterC implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("你好，调通了Filer C实现！");
        return null;
    }
}
