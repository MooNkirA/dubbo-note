package com.moon.dubbo.annotation.service.generic;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.moon.dubbo.service.GenericCallService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

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
public class MyGenericService implements GenericService, ApplicationContextAware {

    private ApplicationContext context;

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
        String result = joiner.toString();
        System.out.println("泛化调用 MyGenericService 实现==> " + result);

        /*
         * 这里做简单的判断，直接调用spring容器中的实例方法。
         * 注：这是写死的调用，实际项目中的应用是通过反射或者从spring容器去调用相应的方法
         */
        if ("getResult".equals(method)) {
            GenericCallService genericCallService = context.getBean(GenericCallService.class);
            result = genericCallService.getResult((String) args[0]);
        }

        return result;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
