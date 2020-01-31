package com.moon.service.impl.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;

/**
 * 使用方传递了group = zero 则该Filter激活
 */
@Activate(group = "zero", order = 2)
public class FilterB implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("你好，调通了Filer B实现！");
        return null;
    }

}
