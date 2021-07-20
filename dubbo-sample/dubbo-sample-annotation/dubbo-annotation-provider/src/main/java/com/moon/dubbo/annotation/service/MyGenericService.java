package com.moon.dubbo.annotation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;

import java.util.StringJoiner;

/**
 * 实现泛化调用，需要实现 dubbo 的 GenericService 接口
 *
 * @author MooNkirA
 * @version 1.0
 * @date 2021-07-18 10:32
 * @description
 */
@Service
public class MyGenericService implements GenericService {

    /**
     * Generic invocation
     *
     * @param method         Method name, e.g. findPerson. If there are overridden methods, parameter info is
     *                       required, e.g. findPerson(java.lang.String)
     * @param parameterTypes Parameter types
     * @param args           Arguments
     * @return invocation return value
     * @throws Throwable potential exception thrown from the invocation
     */
    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        StringJoiner joiner = new StringJoiner("; ", "[ ", " ]");
        joiner.add("method name is " + method);
        if (parameterTypes.length > 0) {
            for (int i = 0; i < parameterTypes.length; i++) {
                joiner.add("parameterType[" + i + "] is " + parameterTypes[i]);
            }
        }
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                joiner.add("args[" + i + "] is " + args[i]);
            }
        }
        System.out.println("泛化调用 MyGenericService 实现==> " + joiner.toString());
        return "泛化调用方法==> " + joiner.toString();
    }

}
